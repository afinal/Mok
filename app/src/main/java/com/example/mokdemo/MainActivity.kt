package com.example.mokdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ctrip.bus.tvm.mok.InitialConfig
import com.ctrip.bus.tvm.mok.Mok
import com.ctrip.bus.tvm.mok.convert.EntityConverter
import com.ctrip.bus.tvm.mok.convert.StringConverter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Mok.init(
            InitialConfig.Builder()
                .baseUrl("https://www.wanandroid.com")  //requested
                .connectionTimeOut(60L)         //optional default 60s
                .build()
        )
        
        Mok.post<WxArticleList>("wxarticle/chapters/json")
            .converter(EntityConverter(WxArticleList::class.java))  //data type converter
            .connectionTimeOut(30L)                 //optional default Mok initial config
            .execute()                                              //create observable
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

        Mok.get<String>("http://www.baidu.com")
            .converter(StringConverter())
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

    }
}
