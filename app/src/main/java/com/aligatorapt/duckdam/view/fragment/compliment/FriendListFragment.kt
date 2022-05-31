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
import com.aligatorapt.duckdam.databinding.FragmentFriendlistBinding
import com.aligatorapt.duckdam.view.activity.FriendListDetailActivity
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.UserCallback
import com.aligatorapt.duckdam.retrofit.callback.UserListCallback
import com.aligatorapt.duckdam.view.adapter.FriendListAdapter
import com.aligatorapt.duckdam.viewModel.FriendSingleton
import com.aligatorapt.duckdam.viewModel.LoginSingleton

class FriendListFragment : Fragment() {
    private var _binding: FragmentFriendlistBinding? = null
    private val binding: FragmentFriendlistBinding get() = _binding!!
    private val DETAIL = 100

    private val friendmodel = FriendSingleton.getInstance()
    private val usermodel = LoginSingleton.getInstance()
    lateinit var selectFriend: UserResponseDto

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

            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            friendlistRv.layoutManager = linearLayoutManager
            val user = usermodel?.user!!.value
            val selectAdapter = FriendListAdapter(requireContext(), arrayListOf(),friendmodel, user)

            selectAdapter.itemClickListener = object: FriendListAdapter.OnItemClickListener{
                override fun OnItemClick(data: UserResponseDto, position: Int) {
                    val intent = Intent(requireContext(), FriendListDetailActivity::class.java)
                    intent.putExtra("data",data)
                    selectFriend = data
                    startActivityForResult(intent, DETAIL)
                }
                override fun OnAddClick(data: UserResponseDto, position: Int) {
                    friendmodel?.followFriend(
                        _targetId = data.uid,
                        object: ApiCallback{
                            override fun apiCallback(flag: Boolean) {
                                if(flag){
                                    Toast.makeText(requireContext(),"친구로 추가되었습니다!",Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                }
            }
            friendlistRv.adapter = selectAdapter

            friendmodel?.findMyFriend(object: UserCallback{
                override fun userCallback(flag: Boolean, data: ArrayList<UserResponseDto>?) {
                    if(flag){
                        if(data!!.isNotEmpty()){
                            friendmodel.setFriend(data)
                            selectAdapter.setData(data,true)
                        }else{
                            emptyResult.visibility = View.VISIBLE
                            friendlistRv.visibility = View.GONE
                        }
                        friendnum.text = data.size.toString()
                    }
                }
            })

            friendlistSearchIv.setOnClickListener {
                //검색 시작
                val imm: InputMethodManager? = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(friendlistSearchEt.getWindowToken(), 0);
                val searchtext = friendlistSearchEt.text.toString()
                friendmodel?.searchByName(searchtext, object: UserListCallback{
                    override fun userListCallback(
                        flag: Boolean,
                        data: ArrayList<UserResponseDto>?
                    ) {
                        if(flag){
                            if(data!!.isNotEmpty()){
                                emptyResult.visibility = View.GONE
                                friendlistRv.visibility - View.VISIBLE
                                selectAdapter.setData(data, false)
                            }
                            else{
                                emptyResult.visibility = View.VISIBLE
                                textView.text = "검색된 친구가 없어요"
                                textView2.visibility = View.GONE
                                friendlistRv.visibility = View.GONE
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            val mActivity = activity as NavigationActivity
            val bundle = Bundle()
            bundle.putSerializable("selectFriend",selectFriend)
            val complimentFragment = ComplimentFragment()
            complimentFragment.arguments = bundle
            mActivity.changeFragment(complimentFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
