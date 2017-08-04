package com.fxl.mvpdemo.pages.amuselist

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fxl.mvpdemo.R
import com.fxl.mvpdemo.model.bean.Amuse

/**
 * Created by Administrator on 2017/6/15.
 *
 */
class AmuseListAdapter(datas: List<Amuse>?) : BaseQuickAdapter<Amuse, BaseViewHolder>(R.layout.item_guid, datas) {
    public val TYPE_1:Int = 1

    override fun convert(helper: BaseViewHolder?, amuse: Amuse?) {
        var type: Int = doSomething(null)
        type++
        if (type == 1) {

        }
    }

    private fun doSomething(type:Int?):Int {
        if (type == null) {
            throw NullPointerException("这个东西不能为空")
        }

        return 0
    }

}