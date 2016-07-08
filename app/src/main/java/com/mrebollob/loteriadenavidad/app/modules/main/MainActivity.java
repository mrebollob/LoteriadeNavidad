package com.mrebollob.loteriadenavidad.app.modules.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.app.modules.main.adapter.LotteryTicketsAdapter;
import com.mrebollob.loteriadenavidad.app.ui.BaseActivity;
import com.mrebollob.loteriadenavidad.app.ui.errors.ErrorManager;
import com.mrebollob.loteriadenavidad.app.ui.errors.SnackbarErrorManagerImp;
import com.mrebollob.loteriadenavidad.app.util.FeedbackUtils;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.LotteryTicketsPresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements LotteryTicketsPresenter.View,
        SwipeRefreshLayout.OnRefreshListener {

    LotteryTicketsPresenter mPresenter;

    ErrorManager errorManager;

    @Bind(R.id.coordinator_layout)
    protected CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.spinner)
    protected Spinner spinner;

    @Bind(R.id.tv_total_bet)
    protected TextView tvTotalBet;
    @Bind(R.id.tv_total_win)
    protected TextView tvTotalWin;
    @Bind(R.id.tv_profit)
    protected TextView tvProfit;
    @Bind(R.id.tv_lottery_status)
    protected TextView tvLotteryStatus;
    @Bind(R.id.tv_last_update)
    protected TextView tvLastUpdate;

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rv_lottery_tickets)
    RecyclerView lotteryTicketsRecyclerView;

    private LotteryTicketsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorManager = new SnackbarErrorManagerImp(coordinatorLayout);
        mPresenter = new LotteryTicketsPresenter();

        initUi();
    }

    private void initUi() {
        initToolbar();
        initInfoTable();
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    public int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule());
    }

    private void initInfoTable() {
        tvTotalBet.setText(getString(R.string.total_bet, 0f));
        tvTotalWin.setText(getString(R.string.total_win, 0f));
        tvProfit.setText(getString(R.string.profit, 0f));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }


    @OnClick(R.id.fab)
    public void onAddButtonClick(View view) {
    }

    private void initRecyclerView() {
        lotteryTicketsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lotteryTicketsRecyclerView.setHasFixedSize(true);
        mAdapter = new LotteryTicketsAdapter(mPresenter);
        lotteryTicketsRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        lotteryTicketsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = recyclerView == null || recyclerView.getChildCount() == 0 ? 0
                        : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_feedback:
                FeedbackUtils.askForFeedback(this);
                return true;
            case R.id.action_about:
                return true;
            case R.id.action_sort_by_label:
                return true;
            case R.id.action_sort_by_number:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.update();
    }

    @Override
    public void showLotteryTickets(List<LotteryTicket> lotteryTickets) {
        mAdapter.addAll(lotteryTickets);
    }

    @Override
    public void showEmptyCase() {

    }

    @Override
    public void openLotteryTicketScreen(LotteryTicket lotteryTicket) {

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
