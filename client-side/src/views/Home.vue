<template>
  <div class="container">
    <div class="course-container">
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col col-xs-6">
              <h3 class="panel-title">Todos os Cursos</h3>
            </div>
          </div>
        </div>

        <div class="card-body">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Titulo</th>
                <th scope="col">Autor</th>
                <th scope="col">Detalhes</th>
                <th scope="col">Ações</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in courses" v-bind:key="item.id">
                <th scope="row">{{index}}</th>
                <td>{{item.title}}</td>
                <td>{{item.author}}</td>
                <td>
                  <button class="btn btn-info" @click="detail(item)">Detail</button>
                </td>
                <td>
                  <button class="btn btn-success" @click="enroll(item)">Enroll</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import UserService from '../service/user.service';
  import CourseService from '../service/course.service';
  import { User } from '../model/User';
  import { Transaction } from '../model/Transaction';
  import courseService from '../service/course.service';

  export default {
    name: 'home',
    data(){
      return {
        courses: [],
        currentUser: new User(),
      }
    },
    created(){
      UserService.currentUser.subscribe(data => {
        this.currentUser = data;
      });
      courseService.findAllCourses().then(data => {
        this.courses = data.data;
      });
    },
    methods: {
      enroll(course){
        if(this.currentUser){
          return ;
        }
        var transaction = new Transaction(this.currentUser.id, course);
        CourseService.createTransaction(transaction).then(() => {

        }, error => {
          console.log(error);
        });
      },
      detail(item){
        localStorage.setItem('detailCourse', JSON.stringify(item));
        this.$router.push({name: 'detail', param: {id: item.id}})
      }
    }
  }

</script>

<style scoped>
</style>
