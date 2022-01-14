package com.example.stocksapp.views.fragments

import android.content.*
import android.graphics.*
import android.os.*
import android.text.*
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.stocksapp.R
import com.example.stocksapp.views.MainActivity
import com.example.stocksapp.views.constants.CoroutineScopeUtils
import java.io.IOException
import java.net.UnknownHostException
import java.util.*

open class BaseFragment : Fragment() {

    protected var mContentView: View? = null
    protected var mActivity: MainActivity? = null
    protected var TAG: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as MainActivity
        Log.d(TAG, "onAttach :: called in Application")
    }

    open fun onClick(view: View?) = Unit

    open fun onBackPressed(): Boolean = false

    fun showToast(message: String? = "sample testing") {
        mActivity?.showToast(message)
    }

    open fun exceptionHandlingForAPIResponse(e: Exception) {
        CoroutineScopeUtils().runTaskOnCoroutineMain {
            when (e) {
                is IllegalStateException -> showToast("System Error :: IllegalStateException :: Unable to reach Server")
                is IOException -> Log.e(TAG, "$TAG exceptionHandlingForAPIResponse: ${e.message}", e)
                is UnknownHostException -> showToast(e.message)
                else -> showToast(mActivity?.getString(R.string.something_went_wrong))
            }
        }
    }

    fun EditText.showKeyboard() {
        mActivity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun EditText.hideKeyboard() {
        mActivity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.windowToken, 0)
        }
    }

    open fun TextView.showStrikeOffText() {
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


    open fun TextView.setMaxLength(length: Int) {
        filters = arrayOf<InputFilter>(LengthFilter(length))
    }

    open fun hideSoftKeyboard() {
        val imm = mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mContentView?.windowToken, 0)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun TextView.setHtmlData(string: String?) {
        string?.let { it ->
            this.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    open fun launchFragment(fragment: Fragment?, addBackStack: Boolean) {
        CoroutineScopeUtils().runTaskOnCoroutineMain {
            mActivity?.launchFragment(fragment, addBackStack)
        }
    }

    open fun clearFragmentBackStack() {
        try {
            val fm = mActivity?.supportFragmentManager
            fm?.let {
                for (i in 0 until it.backStackEntryCount) {
                    it.popBackStack()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "clearFragmentBackStack: ${e.message}", e)
        }
    }

}