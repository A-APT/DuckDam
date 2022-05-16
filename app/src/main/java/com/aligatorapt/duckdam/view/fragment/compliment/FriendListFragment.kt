package com.aligatorapt.duckdam.view.fragment.compliment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentFriendlistBinding
import com.aligatorapt.duckdam.view.activity.FriendListDetailActivity
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.adapter.SelectListAdapter
import com.aligatorapt.duckdam.view.data.SelectList
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService




class FriendListFragment : Fragment() {
    private var _binding: FragmentFriendlistBinding? = null
    private val binding: FragmentFriendlistBinding get() = _binding!!
    private val DETAIL = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.apply {

            friendnum.text = setFriendList().size.toString()

            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            friendlistRv.layoutManager = linearLayoutManager
            val selectAdapter = SelectListAdapter(requireContext(),setFriendList(),true)
            selectAdapter.itemClickListener = object: SelectListAdapter.OnItemClickListener{
                override fun OnItemClick(data: SelectList, position: Int) {
                    val intent = Intent(requireContext(), FriendListDetailActivity::class.java)
                    intent.putExtra("data",data)
                    startActivityForResult(intent, DETAIL)
                }
                override fun OnAddClick(data: SelectList, position: Int) {
                    Toast.makeText(requireContext(),"친구로 추가되었습니다!",Toast.LENGTH_SHORT).show()
                }
            }
            friendlistRv.adapter = selectAdapter

            friendlistSearchIv.setOnClickListener {
                //검색 시작
                val imm: InputMethodManager? = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(friendlistSearchEt.getWindowToken(), 0);
                val searchtext = friendlistSearchEt.text.toString()
                if(searchtext != "")selectAdapter.setData(setSearchFriendList())
                else selectAdapter.setData(setFriendList())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            val selectList = data?.getSerializableExtra("selectList")

            val mActivity = activity as NavigationActivity
            val bundle = Bundle()
            bundle.putSerializable("selectList",selectList)
            val complimentFragment = ComplimentFragment()
            complimentFragment.arguments = bundle
            mActivity.changeFragment(complimentFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setFriendList(): ArrayList<SelectList> {
        return arrayListOf(
            SelectList(
                sticker = R.drawable.small_sample,
                name = "어깨피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "허리피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "맛있는피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "어깨피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "허리피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "맛있는피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "어깨피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "허리피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "맛있는피자",
                isAdded = true
            )
        )
    }

    private fun setSearchFriendList(): ArrayList<SelectList> {
        return arrayListOf(
            SelectList(
                sticker = R.drawable.small_sample,
                name = "어깨피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "허리피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "맛있는피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "파인애플피자",
                isAdded = false
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "민트초코피자",
                isAdded = false
            )
        )
    }
}
