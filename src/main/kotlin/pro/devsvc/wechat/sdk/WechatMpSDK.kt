package pro.devsvc.wechat.sdk

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.util.EntityUtils
import java.net.URLEncoder
import java.util.*
import javax.swing.text.html.parser.Entity


object API {
    const val END_POINT = "https://api.weixin.qq.com/cgi-bin"
    object METHODS {
        // GET methods
        const val ACCESS_TOKEN = "/token"

        // POST methods

    }
}

class WechatMpSDK {

    private val connectionManager = PoolingHttpClientConnectionManager()
    private val client = HttpClients.custom().setConnectionManager(connectionManager).build()

    private lateinit var appKey: String
    private lateinit var appSecret: String
    private lateinit var accessToken: AccessToken


    constructor() {

    }

    /**
     * TOTO： 直接用字符串存储appkey其实并不安全，需要考虑升级安全措施
     */
    constructor(appKey: String, appSecret: String) {
        this.appKey = appKey
        this.appSecret = appSecret
    }

    fun getAccessToken() {

    }


    /**
     * GET方式调用接口方法
     */
    private fun remoteCallGetMethod(method: String, parameters: Map<String, String>) {

    }

    /**
     * http请求封装： get
     */
    private fun httpGet(url: String): String? {
        val get = HttpGet(url)
        val resp = client.execute(get)
        if (resp.statusLine.statusCode == 200) {
            return EntityUtils.toString(resp.entity)
        }
        return null
    }

}


class AccessToken(value: String, expire: Date) {

    fun expired(): Boolean {
        return false
    }
}