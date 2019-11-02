短链接服务：
1）短网址的特点：简 短、易记、易传播，更高效，更简单，可以把非常长的一段网址缩短在10几个字符内，大大的方便了广告链接的推广，除了短网址缩短的功能以及防红基本功能外，还可以拓展高级功能， 如传播用户的基础分析以及更高级的全景用户分析，可以识别用户的设备访问，浏览器访问等。

2）短网址目前主要应用于长网址缩短服务，在短信推广，APP推广、H5的推广、公众号的推广、微信的推广上有极大的便捷性，在电商、直播、游戏、金融、房产、站长、行政、文案 等领域有期独特的性质，那就是简单易记传播方便。

3）测试demo：
转换短链接：http://120.79.41.249:8089/evh/shortUrl/convertToShortUrl?longUrl=www.baidu.com
逆转换为长链接：http://120.79.41.249:8089/evh/shortUrl/reconvertToLongUrl?shortUrl=ZvUfuqzI3IFz77Rz6vuAVrMz:2
短链接重定向到长连接：http://120.79.41.249:8089/evh/shortUrl/redirectToLongUrl?shortUrl=ZvUfuqzI3IFz77Rz6vuAVrMz:2

4）框架业务：
springBoot：web服务框架
jooq：ORM层
redis：用于存储url的seqId、以及短链接与长链接的映射；
mysql：用于存储url转换事件；
kafka：记录转换事件；