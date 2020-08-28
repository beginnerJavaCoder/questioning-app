<template>
  <div>
    <hr>
    <p>Войдите в систему</p>
    <p v-if="badCredentials">Неверный логин или пароль</p>
    <form @submit.prevent="onSubmit">
      <label>
        <input type="text" placeholder="Ваш логин" v-model="username">
      </label>
      <br>
      <label>
        <input type="password" placeholder="Пароль" v-model="password">
      </label>
      <br>
      <button type="submit">Войти</button>
    </form>
    <hr>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  data() {
    return {
      username: '',
      password: '',
      badCredentials: false
    }
  },
  methods: {
    onSubmit() {
      if (this.username.trim() &&
          this.password.trim()) {
        let request = {
          username: this.username,
          password: this.password
        }
        let response = fetch('http://localhost:8081/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(request)
        })
            .then(response => response.json(), () => {
              this.badCredentials = true
            })
            .then(json => {
              this.username = ''
              this.password = ''
              this.$emit('add-user-info', json)
            })

        /*if (response.ok) {
          this.username = ''
          this.password = ''
          const userInfo = await response.json()
          this.$emit('add-user-info', userInfo)
        } else {
          this.badCredentials = true
        }*/
      }
    }
  }
}
</script>

<style scoped>

</style>