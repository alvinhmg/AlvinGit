<template>
  <div id="app">
    <h1>服务端数据为：{{serverData}}</h1>
    <!--<img src="./assets/logo.png" @click="getData()">-->
    <!--<button>-->
      <!--<router-link to="/tableTest">点击这里</router-link>-->
    <!--</button>-->
    <!--<router-view></router-view>-->
  </div>
</template>

<script>
    export default {
      name: 'Test',
      data () {
        return {
          serverData: []
        }
      },
      mounted: function () {
        this.getData()
      },
      methods: {
        getData () {
          let _this = this
          console.log('-------getData--------')
          this.$axios.get('http://localhost:9090/Alvin/user//queryUserById/3')
            .then(function (response) {
              // console.log(response)
              console.log(_this)
              _this.serverData = response.data
            })
            .catch(function (error) {
              console.log(error)
            })
        }
      }

    }
</script>

<style scoped>
</style>
