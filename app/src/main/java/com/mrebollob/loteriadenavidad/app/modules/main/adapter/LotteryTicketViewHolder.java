package com.mrebollob.loteriadenavidad.app.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.LotteryTicketsPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mrebollob
 */
public class LotteryTicketViewHolder extends RecyclerView.ViewHolder {

    private final LotteryTicketsPresenter mPresenter;

    @Bind(R.id.tv_label)
    TextView labelTextView;
    @Bind(R.id.tv_number)
    TextView numberTextView;
    @Bind(R.id.tv_bet)
    TextView betTextView;
    @Bind(R.id.tv_prize)
    TextView prizeTextView;

    public LotteryTicketViewHolder(View itemView, LotteryTicketsPresenter lotteryTicketsPresenter) {
        super(itemView);
        this.mPresenter = lotteryTicketsPresenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(LotteryTicket lotteryTicket) {
        hookListeners(lotteryTicket);

        float mPrize = lotteryTicket.getBet() * lotteryTicket.getPrize() / 20;

        labelTextView.setText(lotteryTicket.getLabel());
        numberTextView.setText(getContext().getString(R.string.number_format, lotteryTicket.getNumber()));
        betTextView.setText(getContext().getString(R.string.money_format, lotteryTicket.getBet()));
        prizeTextView.setText(getContext().getString(R.string.money_format, mPrize));
    }

    private void hookListeners(final LotteryTicket lotteryTicket) {
        itemView.setOnClickListener(v -> mPresenter.onLotteryTicketClicked(lotteryTicket));
    }

    private Context getContext() {
        return itemView.getContext();
    }
}