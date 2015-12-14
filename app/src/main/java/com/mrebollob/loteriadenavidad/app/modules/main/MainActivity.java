package com.mrebollob.loteriadenavidad.app.modules.main;

import android.os.Bundle;
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
import com.mrebollob.loteriadenavidad.app.modules.about.AboutActionCommand;
import com.mrebollob.loteriadenavidad.app.modules.lotteryticketform.LotteryTicketFormActionCommand;
import com.mrebollob.loteriadenavidad.app.modules.main.adapter.DrawSpinnerAdapter;
import com.mrebollob.loteriadenavidad.app.modules.main.adapter.LotteryTicketsListAdapter;
import com.mrebollob.loteriadenavidad.app.ui.BaseActivity;
import com.mrebollob.loteriadenavidad.app.ui.errors.ErrorManager;
import com.mrebollob.loteriadenavidad.app.ui.errors.SnackbarErrorManagerImp;
import com.mrebollob.loteriadenavidad.app.util.FeedbackUtils;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryType;
import com.mrebollob.loteriadenavidad.presentation.modules.main.MainPresenter;
import com.mrebollob.loteriadenavidad.presentation.modules.main.MainView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, LotteryTicketsListAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MainPresenter presenter;

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
        errorManager = new SnackbarErrorManagerImp(coordinatorLayout);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                LotteryTicketFormActionCommand lotteryTicketFormActionCommand =
                        new LotteryTicketFormActionCommand(MainActivity.this);
                lotteryTicketFormActionCommand.execute();
            }
        });

        initUi();
    }

    private void initUi() {
        initToolbar();
        initInfoTable();
        initSpinner();
        initRecyclerView();
        initRefreshLayout();
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
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            LotteryTicketFormActionCommand lotteryTicketFormActionCommand =
                    new LotteryTicketFormActionCommand(this);
            lotteryTicketFormActionCommand.execute();
        }
    }

    private void initRecyclerView() {
        list.setLayoutManager(new LinearLayoutManager(this));
        lotteryTicketsListAdapter = new LotteryTicketsListAdapter(this);
        lotteryTicketsListAdapter.setOnItemClickListener(this);
        list.setAdapter(lotteryTicketsListAdapter);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        list.setOnScrollListener(new RecyclerView.OnScrollListener() {

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
                AboutActionCommand aboutActionCommand = new AboutActionCommand(this);
                aboutActionCommand.execute();
                return true;
            case R.id.action_sort:
                presenter.sortLotteryTickets();
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
    public void refreshLotteryTicketsList(List<PresentationLotteryTicket> lotteryTickets) {

        float totalbet = 0f;
        float totalwin = 0f;

        for (PresentationLotteryTicket lotteryTicket : lotteryTickets) {
            totalbet += lotteryTicket.getBet();
            totalwin += lotteryTicket.getPrize();
        }

        tvTotalBet.setText(getString(R.string.total_bet, totalbet));
        tvTotalWin.setText(getString(R.string.total_win, totalwin));
        tvProfit.setText(getString(R.string.profit, totalwin - totalbet));

        lotteryTicketsListAdapter.updateLotteryTickets(lotteryTickets);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshUi() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showGetLotteryTicketsError() {
        swipeRefreshLayout.setRefreshing(false);
        errorManager.showError(getString(R.string.error_get_lottery_tickets));
    }

    @Override
    public void showDeleteLotteryTicketError() {
        swipeRefreshLayout.setRefreshing(false);
        errorManager.showError(getString(R.string.error_delete_lottery_ticket));
    }

    @Override
    public void showSortLotteryTicketsError() {
        swipeRefreshLayout.setRefreshing(false);
        errorManager.showError(getString(R.string.error_sort_lottery_tickets));
    }

    @Override
    public void showUpdatePrizesError() {
        swipeRefreshLayout.setRefreshing(false);
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
                                LotteryTicketFormActionCommand lotteryTicketFormActionCommand =
                                        new LotteryTicketFormActionCommand(MainActivity.this, lotteryTicket);
                                lotteryTicketFormActionCommand.execute();
                                break;
                            case 1:
                                comfirmDelete(lotteryTicket);
                                break;
                            default:
                                Log.wtf("TAG", "Nunca deberia ver esto");
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
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        if (presenter != null) presenter.deleteLotteryTicket(lotteryTicket.getId());
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
