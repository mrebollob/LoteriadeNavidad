package com.mrebollob.loteriadenavidad.app.modules.lotteryticketform;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.app.ui.BaseActivity;
import com.mrebollob.loteriadenavidad.app.ui.errors.ErrorManager;
import com.mrebollob.loteriadenavidad.app.ui.errors.SnackbarErrorManagerImp;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class LotteryTicketFormActivity extends BaseActivity {

    public static final String LOTTERY_TICKET_EXTRA = "lottery_ticket_extra";

    ErrorManager errorManager;

    @Bind(R.id.coordinator_layout)
    protected CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.et_label)
    protected EditText etLabel;
    @Bind(R.id.et_number)
    protected EditText etNumber;
    @Bind(R.id.et_bet)
    protected EditText etBet;
    @Bind(R.id.rg_lottery)
    protected RadioGroup rgLottery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorManager = new SnackbarErrorManagerImp(coordinatorLayout);
        initUi();
    }

    private void initUi() {
        initToolbar();
    }

    @Override
    public int onCreateViewId() {
        return R.layout.activity_lottery_ticket_form;
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new LotteryTicketFormModule());
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lottery_ticket_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
