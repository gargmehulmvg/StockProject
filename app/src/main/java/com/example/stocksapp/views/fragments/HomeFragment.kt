package com.example.stocksapp.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.R
import com.example.stocksapp.interfaces.IAdapterItemClickListener
import com.example.stocksapp.interfaces.IHomeFragmentInterface
import com.example.stocksapp.models.responses.StockResponse
import com.example.stocksapp.services.HomeFragmentService
import com.example.stocksapp.views.adapters.StockAdapter
import com.example.stocksapp.views.constants.CoroutineScopeUtils
import com.example.stocksapp.views.constants.isEmpty
import kotlinx.android.synthetic.main.layout_home_fragment.*

class HomeFragment : BaseFragment(), IHomeFragmentInterface {

    private var group: Group? = null
    private var recyclerView: RecyclerView? = null
    private val mService: HomeFragmentService = HomeFragmentService()

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContentView = inflater.inflate(R.layout.layout_home_fragment, container, false)
        group = mContentView?.findViewById(R.id.group)
        recyclerView = mContentView?.findViewById(R.id.recyclerView)
        mService.setHomeFragmentInterface(this)
        mService.getStocksData()
        return mContentView
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            profitLossExpandableTextView?.id -> {
                if (View.VISIBLE == group?.visibility) {
                    group?.visibility = View.GONE
                } else group?.visibility = View.VISIBLE
            }
        }
    }

    override fun onSuccessResponse(commonResponse: StockResponse) {
        CoroutineScopeUtils().runTaskOnCoroutineMain {
            if (isEmpty(commonResponse.error)) {
                recyclerView?.apply {
                    layoutManager = LinearLayoutManager(mActivity)
                    addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
                    adapter = StockAdapter(commonResponse.data, object : IAdapterItemClickListener {
                        override fun onAdapterItemClickListener(position: Int) {
                            try {
                                val item = commonResponse.data?.get(position)
                                profitLossExpandableTextView?.callOnClick()
                                val price = (item?.ltp ?: 0.0) * (item?.quantity?:0)
                                currentValueValueTextView?.text = "$price"

                            } catch (e:Exception) {
                                Log.e(TAG, "onAdapterItemClickListener: ${e.message}", e)
                            }
                        }

                    })
                }
            } else {
                showToast(commonResponse.error)
            }
        }
    }

    override fun onException(e: Exception) = exceptionHandlingForAPIResponse(e)

}