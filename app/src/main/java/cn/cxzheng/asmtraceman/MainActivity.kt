package cn.cxzheng.asmtraceman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.cxzheng.asmtraceman.test.RandomTest
import cn.cxzheng.tracemanui.MethodTraceServerManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val randomTest by lazy { RandomTest() }
    private val random by lazy { Random() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MethodTraceServerManager.logLevel = MethodTraceServerManager.MTM_LOG_DETAIL

        setContentView(R.layout.activity_main)


        btn_click.setOnClickListener {

            val randomMill = rand(0, 30)
            Toast.makeText(this, "已执行随机耗时方法", Toast.LENGTH_SHORT).show()
            randomTest.randomSleep(randomMill.toLong())
        }

    }

    private fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }


}
