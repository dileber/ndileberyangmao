package com.zhonghua.shidawei.nhaoyang.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.utils.Check;
import com.zhonghua.shidawei.nhaoyang.R;
import com.zhonghua.shidawei.nhaoyang.activity.home.dummy.ArticleContent;
import com.zhonghua.shidawei.nhaoyang.contract.HomeContract;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.PageWrapper;
import com.zhonghua.shidawei.nhaoyang.data.source.ArticleRepository;
import com.zhonghua.shidawei.nhaoyang.view.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HomeItemFragment extends BaseFragment implements HomeContract.View{

    private HomeContract.Presenter mPresenter;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HomeItemFragment newInstance() {
        HomeItemFragment fragment = new HomeItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    MyHomeItemRecyclerViewAdapter adapter;
    ArticleRepository articleRepository = ArticleRepository.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homeitem_list, container, false);

        RecyclerView recyclerView = getView (view,R.id.list);
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyHomeItemRecyclerViewAdapter(null, mListener);
        recyclerView.setAdapter(adapter);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(recyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadArticle(true);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * 加载
     * @param active
     */
    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_widget);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showArticle(List<ArtModel> artModelList) {
        adapter.replaceData(artModelList);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = Check.checkNotNull(presenter);
    }

    @Override
    public void toast(String msg, int duration) {
        Toast.makeText(getView().getContext(),msg,duration);
    }

    @Override
    public void showAlert(Integer type, String message) {
        UDialog.alert(type,message).show();
    }

    @Override
    public void loading() {

    }

    @Override
    public void loadDialogDismiss() {

    }

    @Override
    public void dialogOk(String content, DialogLinstener dialogLinstener) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ArtModel item);
    }
}
