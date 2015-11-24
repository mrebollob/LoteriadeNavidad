package com.mrebollob.loteriadenavidad.app.modules.main.adapter;

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
public class LotteryTicketsListAdapter extends RecyclerView.Adapter<LotteryTicketsListAdapter.ViewHolder> {

    private List<PresentationLotteryTicket> lotteryTickets = Collections.emptyList();

    @Override
    public LotteryTicketsListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lottery_ticket_item,
                viewGroup, false);
        return new ViewHolder(view);
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

        viewHolder.text.setText("" + lotteryTicket.getNumber());
    }

    public void updateLotteryTickets(List<PresentationLotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text)
        protected TextView text;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}