package com.mrebollob.loteriadenavidad.app.modules.main.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mrebollob
 */
public class LotteryTicketsListAdapter extends RecyclerView.Adapter<LotteryTicketsListAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<PresentationLotteryTicket> lotteryTickets = Collections.emptyList();
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    @Override
    public LotteryTicketsListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lottery_ticket_item,
                viewGroup, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public LotteryTicketsListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return lotteryTickets.size();
    }

    @Override
    public void onBindViewHolder(LotteryTicketsListAdapter.ViewHolder viewHolder, int i) {
        PresentationLotteryTicket lotteryTicket = lotteryTickets.get(i);
        renderLotteryTicket(lotteryTicket, viewHolder);
    }

    private void renderLotteryTicket(PresentationLotteryTicket lotteryTicket, ViewHolder viewHolder) {

        Resources res = mContext.getResources();

        viewHolder.tvLabel.setText(lotteryTicket.getLabel());

        String numberText = String.format(res.getString(R.string.number_format), lotteryTicket.getNumber());
        viewHolder.tvNumber.setText(numberText);

        String betText = String.format(res.getString(R.string.money_format), lotteryTicket.getBet());
        viewHolder.tvBet.setText(betText);

        float mPrize = lotteryTicket.getBet()*lotteryTicket.getPrize()/20;

        String prizeText = String.format(res.getString(R.string.money_format), mPrize);

        viewHolder.tvPrize.setText(prizeText);
        viewHolder.itemView.setTag(lotteryTicket);

        int color;
        switch (lotteryTicket.getLotteryType()) {
            case CHILD:
                color = mContext.getResources().getColor(R.color.child_lottery);
                break;
            default:
                color = mContext.getResources().getColor(R.color.christmas_lottery);
                break;
        }
        viewHolder.cvTicket.setCardBackgroundColor(color);
    }

    public void updateLotteryTickets(List<PresentationLotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(final View v) {
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (PresentationLotteryTicket) v.getTag());
                }
            }, 200);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_ticket)
        protected CardView cvTicket;
        @Bind(R.id.tv_label)
        protected TextView tvLabel;
        @Bind(R.id.tv_number)
        protected TextView tvNumber;
        @Bind(R.id.tv_bet)
        protected TextView tvBet;
        @Bind(R.id.tv_prize)
        protected TextView tvPrize;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, PresentationLotteryTicket lotteryTicket);
    }
}