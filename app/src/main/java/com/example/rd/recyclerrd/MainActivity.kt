package com.example.rd.recyclerrd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

import com.example.rd.recyclerrd.entity.Task
import com.google.gson.Gson


class MainActivity : AppCompatActivity(),View.OnClickListener {

    var taskList:ArrayList<Task> = ArrayList()
    lateinit var msgRecyclerView: RecyclerView
    lateinit var adapter: QstAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addWenDaTi: Button = findViewById(R.id.addwendati) as Button
        var addDanXuanTi: Button = findViewById(R.id.adddanxuanti) as Button
        var addDuoXuanTi: Button = findViewById(R.id.addduoxuanti) as Button
        var jiance: Button = findViewById(R.id.jiancha) as Button
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
                taskList.add(task)
                adapter.notifyItemInserted(taskList.size-1)
                msgRecyclerView.scrollToPosition(taskList.size-1)
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
                v.isFocusable = true
                v.isFocusableInTouchMode = true
                v.requestFocus()
                var gson: Gson = Gson()
                Log.d("值为",gson.toJson(taskList))
                v.isFocusable = false
                v.isFocusableInTouchMode = false
            }
        }
    }
}
