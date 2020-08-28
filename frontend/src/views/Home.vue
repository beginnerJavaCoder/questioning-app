<template>
  <div>
    <h1>На этом сайте можно создавать и проходить анкеты</h1>
    <h1>Have fun!</h1>
    <RegistrationForm v-if="userInfo.token === ''"/>
    <LoginForm
        v-if="userInfo.token === ''"
        @add-user-info="addUserInfo"/>
    <UserProfile
        :user-info="userInfo"
        v-if="userInfo.token !== ''"/>
  </div>
</template>

<script>
import LoginForm from "@/components/LoginForm";
import RegistrationForm from "@/components/RegistrationForm";
import UserProfile from "@/components/UserProfile";

export default {
  name: 'Home',

  data() {
    return {
      userInfo: {
        token: '',
        userId: Number,
        username: String
      }
    }
  },

  methods: {
    addUserInfo(userInfo) {
      this.userInfo = userInfo
    }
  },

  components: {
    LoginForm,
    RegistrationForm,
    UserProfile
  }

}
</script>

<style scoped>

</style>
