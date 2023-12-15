<template>
    <div class="tag-demo-wrapper">
        <d-tag v-for="item in tagList" deletable :key="item" @tag-delete="() => handleClose(item)">{{ item }}</d-tag>
    </div>
</template>
<script>
import { defineComponent, ref } from 'vue';

export default defineComponent({
    setup() {
        const tagList = ref(['tag1', 'tag2', 'tag3']);
        const handleClose = (item) => {
            tagList.value.splice(tagList.value.indexOf(item), 1);
        };
        return {
            tagList,
            handleClose,
        };
    },
});
</script>
  