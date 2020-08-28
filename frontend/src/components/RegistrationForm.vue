<template>
  <div>
    <hr>
    <p>Регистрация</p>
    <p>{{statusOfRegistration}}</p>
    <form @submit.prevent="onSubmit">
      <label>
        <input type="text" placeholder="Как вас зовут?" v-model="name">
      </label>
      <br>
      <label>
        <input type="text" placeholder="Логин" v-model="username">
      </label>
      <br>
      <label>
        <input type="password" placeholder="Пароль" v-model="password">
      </label>
      <br>
      <button type="submit">Зарегистрироваться</button>
    </form>
    <hr>
  </div>
</template>

<script>
export default {
  name: 'RegistrationForm',

  data() {
    return {
      name: '',
      username: '',
      password: '',
      statusOfRegistration: ''
    }
  },

  methods: {
    async onSubmit() {
      if (this.name.trim() &&
          this.username.trim() &&
          this.password.trim()) {

        let request = {
          name: this.name,
          username: this.username,
          password: this.password
        }
        let response = await fetch('http://localhost:8081/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(request)
        })
        if (response.ok) {
          this.statusOfRegistration = `Пользователь ${this.username} успешно зарегистрирован!`
          this.name = ''
          this.username = ''
          this.password = ''
        } else {
          this.statusOfRegistration = `Пользователь с логином ${this.username} уже существует!`
        }
      }
    }
  }
}
</script>

<style scoped>

</style>