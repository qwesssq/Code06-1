package com.example.code06.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.code06.Home_Recycleview.Mydynamic;
import com.example.code06.Home_Recycleview.WaterfallAdapter;
import com.example.code06.Message_ListView.MessageAdapter;
import com.example.code06.Message_ListView.MyMessage;
import com.example.code06.R;
import com.example.code06.ui.dashboard.DashboardViewModel;
import com.example.code06.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DashboardViewModel dashboardViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mlayoutmanager;
    private MessageAdapter mAdapter;
    private Object ButtonNagivation;

    private NotificationsViewModel notificationsViewModel;
    private List<MyMessage> messagelist = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*********************设置消息列表*****************************/
        RecyclerView mRecyclerView = view.findViewById(R.id.Rv_message);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MessageAdapter(this.getActivity(),buildData());
        mlayoutmanager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mlayoutmanager);
        mRecyclerView.setAdapter(mAdapter);
        /***********************************************************/

        return view;
    }
    private List<MyMessage> buildData(){
        String[] titles = new String[]{"系统通知", "成长助手", "推送消息"};
        String[] details = new String[]{"协议更新通知", "获得生日限时贴纸，仅今日有效", "为你生成2021年终总结"};
        String[] times = new String[]{"08-15", "08-16", "08-17"};
        String[] imgUris = {"https://img2.baidu.com/it/u=692991362,912968970&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=692991362,912968970&fm=26&fmt=auto&gp=0.jpg",
                "https://img2.baidu.com/it/u=692991362,912968970&fm=26&fmt=auto&gp=0.jpg",

        };
        List<MyMessage> list = new ArrayList<>();
        for (int i=0;i<3;i++){
            MyMessage m = new MyMessage(imgUris[i],titles[i], details[i],times[i]);
            list.add(m);
        }
        return list;
    }
}