# Mok
This is a simple http library based on Retrofit and RxJava
这是一个基于Retrofit和RxJava封装的网络请求库，依赖使用都非常的简单方便。
**持续更新中。。。**

## 添加依赖
```gradle
implementation 'com.bus.tvm:mok:0.0.1'
```

## 初始化
```kotlin
        Mok.init(
            InitialConfig.Builder()
                .baseUrl("https://www.wanandroid.com")  //requested
                .connectionTimeOut(60L)         //optional default 60s
                .build()
        )
```
#### 说明

 1. **baseUrl**必需字段
 2. **connectionTimeOut**非必须字段，默认60秒

## 使用方式
### GET

```kotlin
        Mok.get<String>("http://www.baidu.com")
            .converter(StringConverter())//类型转换器
            .execute()
            .subscribe(object : Observer<String> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d("TAG", t)
                }

                override fun onError(e: Throwable) {
                }

            })
```

### POST

```kotlin
        Mok.post<WxArticleList>("wxarticle/chapters/json")
            .converter(EntityConverter(WxArticleList::class.java))  //data type converter
            .connectionTimeOut(30L)   //optional default Mok initial config
            .execute()   //create observable
            .subscribe(object : Observer<WxArticleList> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: WxArticleList) {
                    Log.d("TAG", t.toString())
                }

                override fun onError(e: Throwable) {
                }
            })
```
#### 说明

 1. 发起网络请求时需要手动指定返回值类型，**如果返回值是实体对象，需要使用EntityConverter,如果返回值是String，需要使用StringConverter**
 2. 超时时间如果不指定，会使用全局的超时时间
 3. **execute**调用产生被观察者对象，然后**subscribe**订阅，走RxJava流程
