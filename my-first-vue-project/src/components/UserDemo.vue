<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
</head>
<script type="text/javascript" src="js/vue.js" ></script>
<link rel="stylesheet" href="css/bootstrap.css" />
<body>
<div id="app">

  <div class="panel panel-primary">
    <div class="panel-heading">
      <h3 class="panel-title">添加品牌</h3>
    </div>
    <form @submit.prevent="submit">
      <div class="panel-body form-inline">
        <label>
          Id:
          <input type="text"  name="id" v-model="addData.id" class="form-control">

        </label>
        <label>
          Name:
          <input type="text" v-model="addData.name" class="form-control"/>
        </label>
        <label>
          Age:
          <input type="text" v-model="addData.age" class="form-control"/>
        </label>
        <input type="submit"  value="添加"  class="btn btn-primary"/>
      </div>
    </form>


  </div>


  <table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Age</th>
      <th>删除</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="item in content" :key="item.id">
      <th>{{ item.id }}</th>
      <th>{{ item.name }}</th>
      <th>{{ item.age }}</th>
      <th>
        <a href="" @click.prevent="del(item.id)">删除</a>
      </th>
    </tr>
    </tbody>



  </table>

</div>

<script>

  var vm = new Vue({

    el:'#app',
    data:{
      content: [
        {
          id: '',
          name: '',
          age: ''
        }
      ],
      addData:{},

    },
    created() { //当vm实例的data和methods 初始化完毕后，vm实例会自动执行这个方法
      this.getAllList();

    },
    methods:{

      getAllList() {  //获取列表的全部信息

        //这个url填写你获取数据的地址	 我的地址http://localhost:8080/ssm/select.action
        var url = ''
        fetch(url,{
          method: 'get'
        })
          .then(res => {
            return res.json()
          })
          .then(data => {
            console.log(data);
            this.content = data;
          })
      },
      submit: function () {
        let data = JSON.stringify(this.addData)
        this.addData = {}
        //这个url添加你添加数据的地址
        let url = ''

        fetch(url, {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: data
        }).then((res) => {

          this.getAllList()
          console.log(res);

        })

      },
      del(id) {

        let url = 'http://localhost:8080/ssm/del.action?id='+id
        fetch(url, {
          method: 'get',
        }).then((res) => {

          this.getAllList()
          console.log(res);

        })
      }
    }

  })

</script>

</body>
</html>
