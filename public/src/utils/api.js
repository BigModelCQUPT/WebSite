import axios from 'axios'

export function export_retailer () {
    // eslint-disable-next-line no-undef
    return axios({
        method:'get',
        url: 'http://localhost:8181/download/aaa',
        responseType: 'blob'
    }).then(res => {
        let data = res.data
        let filename = "居民信息表.xlsx"
        if(res.headers.filename){
            filename = decodeURI(res.headers.filename)
        }
        let url = window.URL.createObjectURL(new Blob([data]))
        let link = document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download',filename)
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link) // 下载完成移除元素
        window.URL.revokeObjectURL(url) // 释放掉blob对象
    }).catch((error) => {
        console.log(error)
    })

}
