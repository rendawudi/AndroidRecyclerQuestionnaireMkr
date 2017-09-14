package com.example.rd.recyclerrd

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.rd.recyclerrd.entity.Task


/**
 * Created by rd on 2017/9/11.
 */

class QstAdapter: RecyclerView.Adapter<QstAdapter.ViewHolder> {
    private var mQstList: ArrayList<Task>               //绑定数据源

    constructor(msgList: ArrayList<Task>) {
        mQstList = msgList
    }


    class ViewHolder : RecyclerView.ViewHolder {

        var selectionNum = 0                            //记录选项个数
        var selectionEdit: ArrayList<EditText>          //储存每个选项上控件的实例
        var selectionButton: ArrayList<Button>

        var delBok: Boolean = true                      //判断是否点击删除问题按钮

        var wendatiLayout: LinearLayout                 //储存每个问题的控件实例
        var danxuantiLayout: LinearLayout

        var wendatiButton: Button
        var danxuantiButton: Button

        var addSelectionDanButton: Button

        var wendatiEdit: EditText
        var danxuantiEdit: EditText

        constructor(view: View, seleced: ArrayList<EditText>, selecbu: ArrayList<Button>) : super(view) {
            selectionNum = selecbu.size                                         //绑定选项数量

            selectionEdit = seleced
            selectionButton = selecbu

            wendatiLayout = view.findViewById(R.id.wendatiLayout) as LinearLayout   //绑定布局和控件
            danxuantiLayout = view.findViewById(R.id.danxuanLayout) as LinearLayout

            wendatiButton = view.findViewById(R.id.delWendati) as Button
            danxuantiButton = view.findViewById(R.id.delDanxuanti) as Button

            addSelectionDanButton = view.findViewById(R.id.addselectionDan) as Button

            wendatiEdit = view.findViewById(R.id.editWendati) as EditText
            danxuantiEdit = view.findViewById(R.id.editDanxuanti) as EditText
        }
    }


    override fun getItemViewType(position: Int): Int {
        return mQstList.get(position).questions.size            //每次需要获得view时进行返回选项个数，个数即代表类型
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
        var selectionEdit: ArrayList<EditText> = ArrayList()    //创建选项控件实例
        var selectionButton: ArrayList<Button> = ArrayList()
        for (i in 0..viewType - 1) {
            var linLayout: LinearLayout = view.findViewById(R.id.danxuanLayout) as LinearLayout     //将选项布局读取并绑定到当前问题布局
            val itemLayout: LayoutInflater = LayoutInflater.from(view.context)
            var selectionLayout: LinearLayout = itemLayout.inflate(R.layout.selection, null).findViewById(R.id.selectionLayout) as LinearLayout
            linLayout.addView(selectionLayout)
            selectionEdit.add(selectionLayout.findViewById(R.id.editSelection) as EditText)         //添加实例
            selectionButton.add(selectionLayout.findViewById(R.id.delSelection) as Button)
        }
        var holder: ViewHolder = ViewHolder(view, selectionEdit, selectionButton)
        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {          //每次读取数据源时
        var task: Task = mQstList.get(position)
        var selection: Int = 0
        selection = task?.questions.size - 1
        when (task.type) {
            "1" -> {                                                              //对不同的问题类型进行布局的隐藏和展示
                holder.danxuantiLayout.visibility = View.GONE
                holder.wendatiLayout.visibility = View.VISIBLE
            }
            "2" -> {
                holder.danxuantiLayout.visibility = View.VISIBLE
                holder.wendatiLayout.visibility = View.GONE
            }
            "3" -> {
                holder.danxuantiLayout.visibility = View.VISIBLE
                holder.wendatiLayout.visibility = View.GONE
            }
        }
        when (task.type) {
            "1" -> {
                holder.wendatiButton.setOnClickListener {                       //问题选项删除按钮
                    if (holder.delBok) {
                        holder.delBok = false
                        mQstList.removeAt(position)                             //移除选项并刷新
                        notifyItemRemoved(position)
                        notifyItemChanged(position, itemCount)
                        Thread(Runnable {
                            Thread.sleep(200)                             //动画时间一过就恢复删除
                            holder.delBok = true
                        }).start()
                    }
                }
                holder.wendatiEdit.setText(task.title.toString())               //读取数据源已经存在的内容
                holder.wendatiEdit.setSelection(task.title.toString().length)
                holder.wendatiEdit.setOnFocusChangeListener { vi, hasFocus ->   //获取焦点
                    if (hasFocus == false)
                    {
                        var s: String = ""                                        //焦点不存在时实时读取已经输入的内容
                        s = holder.wendatiEdit.text.toString()
                        Log.d("selection",s)
                        mQstList[position].title = s
                    }
                }
            }
            "2" -> {
                holder.danxuantiButton.setOnClickListener {
                    if (holder.delBok) {
                        holder.delBok = false
                        mQstList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, itemCount)
                        Thread(Runnable {
                            Thread.sleep(200)
                            holder.delBok = true
                        }).start()
                    }
                }
                holder.danxuantiEdit.setText(task.title.toString())
                holder.danxuantiEdit.setSelection(task.title.toString().length)
                holder.danxuantiEdit.setOnFocusChangeListener{ vi, hasFocus ->
                    if (hasFocus == false)
                    {
                        var s: String = ""
                        s = holder.danxuantiEdit.text.toString()
                        Log.d("selection",s)
                        mQstList[position].title = s
                    }
                }
                holder.addSelectionDanButton.setOnClickListener {       //添加选项
                    var shuru: String = ""
                    mQstList[position].questions.add(shuru)
                    holder.selectionNum++
                    notifyItemChanged(position)                             //刷新当前item
                }

            }
            "3" -> {
                holder.danxuantiEdit.setOnClickListener {
                    if (holder.delBok) {
                        holder.delBok = false
                        mQstList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, itemCount)
                        Thread(Runnable {
                            Thread.sleep(200)
                            holder.delBok = true
                        }).start()
                    }
                }
                holder.danxuantiEdit.setText(task.title.toString())
                holder.danxuantiEdit.setSelection(task.title.toString().length)
                holder.danxuantiEdit.setOnFocusChangeListener{ vi, hasFocus ->
                    if (hasFocus == false)
                    {
                        var s: String = ""
                        s = holder.danxuantiEdit.text.toString()
                        Log.d("selection",s)
                        mQstList[position].title = s
                    }
                }
                holder.addSelectionDanButton.setOnClickListener{
                    var shuru:String = ""
                    mQstList[position].questions.add(shuru)
                    holder.selectionNum++
                    notifyItemChanged(position)
                }
            }
        }
        when (task.type) {
            "2" -> {
                for (i in 0..selection) {
                    holder.selectionEdit.get(i).setText(task.questions.get(i).toString())
                    holder.selectionEdit.get(i).setSelection(task.questions.get(i).toString().length)
                    holder.selectionEdit[i].setOnFocusChangeListener { vi, hasFocus ->
                        if (hasFocus == false)
                        {
                            var s: String = ""
                            s = holder.selectionEdit[i].text.toString()
                            Log.d("selection",s)
                            mQstList[position].questions.set(i,s)
                        }
                    }
                    holder.selectionButton[i].setOnClickListener {
                        mQstList[position].questions.removeAt(i)
                        holder.selectionNum--
                        notifyItemChanged(position)
                    }
                }
            }

            "3" ->{
                for(i in 0..selection)
                {
                    holder.selectionEdit.get(i).setText(task.questions.get(i).toString() )
                    holder.selectionEdit.get(i).setSelection(task.questions.get(i).toString().length)
                    holder.selectionEdit[i].setOnFocusChangeListener { vi, hasFocus ->
                        if (hasFocus == false)
                        {
                            var s: String = ""
                            s = holder.selectionEdit[i].text.toString()
                            Log.d("selection",s)
                            mQstList[position].questions.set(i,s)
                        }
                    }
                    holder.selectionButton[i].setOnClickListener {
                        mQstList[position].questions.removeAt(i)
                        holder.selectionNum--
                        notifyItemChanged(position)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mQstList.size
    }
}