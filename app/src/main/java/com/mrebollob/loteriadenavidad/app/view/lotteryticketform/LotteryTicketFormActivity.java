/*
 * Copyright 2016 Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.app.view.lotteryticketform;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.app.util.errors.ErrorManager;
import com.mrebollob.loteriadenavidad.app.util.errors.SnackbarErrorManagerImp;
import com.mrebollob.loteriadenavidad.app.view.BaseActivity;

import butterknife.BindView;

public class LotteryTicketFormActivity extends BaseActivity {

    public static final String LOTTERY_TICKET_EXTRA = "lottery_ticket_extra";

    ErrorManager errorManager;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.et_label)
    EditText etLabel;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_bet)
    EditText etBet;
    @BindView(R.id.rg_lottery)
    RadioGroup rgLottery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorManager = new SnackbarErrorManagerImp(coordinatorLayout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lottery_ticket_form;
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
