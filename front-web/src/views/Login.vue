<template>
    <div>
        21331
    </div>
</template>

<script name="Login" lang="ts" setup>
import { Base64 } from 'js-base64'
import { type Response } from '@/lin/interface/type'
import User from '@/lin/model/admin'
import app from '@/main'
import { saveTokens } from '@/lin/util/token'
import { onMounted } from 'vue';
import { ref } from 'vue';
const loading = ref(false);
async function login({ username, password }: { username: string, password: string }) {
    loading.value = true;
    const data = {
        username,
        password
        // password: Base64.encode(password)
    }
    const tokens = await User.getToken(data) as Response;
    loading.value = false;
    if (tokens.code === 0) {
        saveTokens(tokens.data.token)
    } else {
        app.$message.error(tokens.message)
    }
}

onMounted(async () => {
    await login({ username: '111', password: '2888' });
})

</script>

<style lang="scss" scoped>
div {
    background-color: $blue;
}
</style>