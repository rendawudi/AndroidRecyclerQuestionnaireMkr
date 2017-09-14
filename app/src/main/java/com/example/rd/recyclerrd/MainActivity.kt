package com.example.rd.recyclerrd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button

import com.example.rd.recyclerrd.entity.Task
import com.google.gson.Gson


class MainActivity : AppCompatActivity(),View.OnClickListener {

    var taskList:ArrayList<Task> = ArrayList()                      //数据源创建
    lateinit var msgRecyclerView: RecyclerView
    lateinit var adapter: QstAdapter                               //适配器

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addWenDaTi: Button = findViewById(R.id.addwendati) as Button        //增加问答题按钮
        var addDanXuanTi: Button = findViewById(R.id.adddanxuanti) as Button    //增加单选题按钮
        var addDuoXuanTi: Button = findViewById(R.id.addduoxuanti) as Button    //增加多选题按钮
        var jiance: Button = findViewById(R.id.jiancha) as Button               //保存按钮
        msgRecyclerView = findViewById(R.id.task_recycler_view) as RecyclerView
        var layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        msgRecyclerView.layoutManager = layoutManager
        adapter = QstAdapter(taskList)
        msgRecyclerView.adapter = adapter

        msgRecyclerView.itemAnimator.changeDuration = 0
        addDanXuanTi.setOnClickListener(this)
        addWenDaTi.setOnClickListener(this)
        addDuoXuanTi.setOnClickListener(this)
        jiance.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.adddanxuanti ->{
                var task:Task = Task()
                task.type = "2"
                task.title = ""
                taskList.add(task)                                          //增加一个单选题并将问题描述赋值
                adapter.notifyItemInserted(taskList.size-1)      //提示Recycler刷新
                msgRecyclerView.scrollToPosition(taskList.size-1)//将屏幕移动至合适的位置
            }
            R.id.addwendati ->{
                var task:Task = Task()
                task.type = "1"
                task.title = ""
                taskList.add(task)
                adapter.notifyItemInserted(taskList.size-1)
                msgRecyclerView.scrollToPosition(taskList.size-1)
            }
            R.id.addduoxuanti ->{
                var task:Task = Task()
                task.type = "3"
                task.title = ""
                taskList.add(task)
                adapter.notifyItemInserted(taskList.size-1)
                msgRecyclerView.scrollToPosition(taskList.size-1)
            }
            R.id.jiancha ->{
                v.isFocusable = true                                //改为焦点模式
                v.isFocusableInTouchMode = true
                v.requestFocus()                                     //获得焦点
                var gson: Gson = Gson()                             //测试使用，将所有问题转为json输出检查是否绑定数据
                Log.d("值为",gson.toJson(taskList))
                v.isFocusable = false                               //取消焦点模式
                v.isFocusableInTouchMode = false
            }
        }
    }
}
