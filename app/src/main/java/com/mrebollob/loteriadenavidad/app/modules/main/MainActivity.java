package com.mrebollob.loteriadenavidad.app.modules.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.app.modules.main.adapter.DrawSpinnerAdapter;
import com.mrebollob.loteriadenavidad.app.modules.main.adapter.LotteryTicketsListAdapter;
import com.mrebollob.loteriadenavidad.app.ui.BaseActivity;
import com.mrebollob.loteriadenavidad.app.ui.errors.ErrorManager;
import com.mrebollob.loteriadenavidad.app.ui.errors.SnackbarErrorManagerImp;
import com.mrebollob.loteriadenavidad.app.util.AnalyticsManager;
import com.mrebollob.loteriadenavidad.app.util.FeedbackUtils;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryType;
import com.mrebollob.loteriadenavidad.presentation.modules.main.MainPresenter;
import com.mrebollob.loteriadenavidad.presentation.modules.main.MainView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, LotteryTicketsListAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainPresenter presenter;
    @Inject
    AnalyticsManager analyticsManager;

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
    protected SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.list)
    protected RecyclerView list;

    private LotteryTicketsListAdapter lotteryTicketsListAdapter;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        analyticsManager.sendScreenView(MainActivity.class.getSimpleName());
        errorManager = new SnackbarErrorManagerImp(coordinatorLayout);

        initInterstitialAd();
        initUi();
    }

    private void initUi() {
        initToolbar();
        initInfoTable();
        initSpinner();
        initRecyclerView();
        initRefreshLayout();
    }

    private void initInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.test_interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                presenter.onAdClosed();
            }
        });
    }

    @Override
    public int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

    private void initInfoTable() {
        tvTotalBet.setText(getString(R.string.total_bet, 0f));
        tvTotalWin.setText(getString(R.string.total_win, 0f));
        tvProfit.setText(getString(R.string.profit, 0f));
//        tvLastUpdate.setText(getString(R.string.last_update, "El sorteo no ha empezado"));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initSpinner() {

        String[] draws = getResources().getStringArray(R.array.draws);

        spinner.setAdapter(new DrawSpinnerAdapter(toolbar.getContext(), draws));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        presenter.onSelectLotteryType(PresentationLotteryType.ALL);
                        break;
                    case 1:
                        presenter.onSelectLotteryType(PresentationLotteryType.CHRISTMAS);
                        break;
                    case 2:
                        presenter.onSelectLotteryType(PresentationLotteryType.CHILD);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @OnClick(R.id.fab)
    public void onAddButtonClick(View view) {
        presenter.onAddLotteryTicketClick();
    }

    private void initRecyclerView() {
        list.setLayoutManager(new LinearLayoutManager(this));
        lotteryTicketsListAdapter = new LotteryTicketsListAdapter(this);
        lotteryTicketsListAdapter.setOnItemClickListener(this);
        list.setAdapter(lotteryTicketsListAdapter);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                presenter.onAboutClick();
                return true;
            case R.id.action_sort_by_label:
                presenter.sortLotteryTicketsByLabel();
                return true;
            case R.id.action_sort_by_number:
                presenter.sortLotteryTicketsByNumber();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        if (!mInterstitialAd.isLoaded()) {
            requestNewInterstitial();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLotteryTicketList(List<PresentationLotteryTicket> lotteryTickets) {
        lotteryTicketsListAdapter.updateLotteryTickets(lotteryTickets);
    }

    @Override
    public void showLastUpdate(Date lastUpdate) {
        String lastUpdateText = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(lastUpdate);
        tvLastUpdate.setText(getString(R.string.last_update, lastUpdateText));
    }

    @Override
    public void showLotteryStatus(int status) {
        String lotteryStatusText;

        switch (status) {
            case 0:
                lotteryStatusText = getString(R.string.the_draw_has_not_begun);
                break;
            case 1:
                lotteryStatusText = getString(R.string.the_draw_has_begun);
                break;
            default:
                lotteryStatusText = getString(R.string.the_draw_is_over);
                break;
        }

        tvLotteryStatus.setText(lotteryStatusText);
    }

    @Override
    public void showLotteryNotStarted() {
        swipeRefreshLayout.setRefreshing(false);
        errorManager.showError(getString(R.string.the_draw_has_not_begun));
    }

    @Override
    public void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void showGetLotteryTicketsError() {
        errorManager.showError(getString(R.string.error_get_lottery_tickets));
    }

    @Override
    public void showNoLotteryTicketsError() {
        errorManager.showError(getString(R.string.error_no_numbers));
    }

    @Override
    public void showDeleteLotteryTicketError() {
        errorManager.showError(getString(R.string.error_delete_lottery_ticket));
    }

    @Override
    public void showUpdatePrizesError() {
        errorManager.showError(getString(R.string.error_update_prizes));
    }

    @Override
    public void showLotteryStatusError() {
        errorManager.showError(getString(R.string.error_update_prizes));
    }

    @Override
    public void onItemClick(View view, final PresentationLotteryTicket lotteryTicket) {
        String[] options = {"Editar", "Eliminar"};

        new MaterialDialog.Builder(this)
                .title(lotteryTicket.getLabel())
                .items(options)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        switch (which) {
                            case 0:
                                presenter.onEditLotteryTicketClick(lotteryTicket);
                                break;
                            case 1:
                                comfirmDelete(lotteryTicket);
                                break;
                            default:
                                Log.wtf(TAG, "Nunca deberia ver esto");
                                break;
                        }
                    }
                })
                .show();
    }

    private void comfirmDelete(final PresentationLotteryTicket lotteryTicket) {
        new MaterialDialog.Builder(this)
                .title("Eliminar numero")
                .content("Seguro que quieres eliminar el numero 213213")
                .positiveText("Eliminar")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (presenter != null) presenter.onDeleteLotteryTicketClick(lotteryTicket.getId());
                        analyticsManager.sendEvent("Functions", "Comfirm delete", "Delete number");
                    }
                })
                .negativeText("Cancelar")
                .show();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("9F9ECDD1FE8FF910D411658471E3B73E")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
