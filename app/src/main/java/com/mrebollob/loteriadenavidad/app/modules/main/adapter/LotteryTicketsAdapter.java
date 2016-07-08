package com.mrebollob.loteriadenavidad.app.modules.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.LotteryTicketsPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mrebollob
 */
public class LotteryTicketsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LotteryTicketsPresenter mPresenter;
    private final List<LotteryTicket> mLotteryTickets;

    public LotteryTicketsAdapter(LotteryTicketsPresenter presenter) {
        this.mPresenter = presenter;
        this.mLotteryTickets = new ArrayList<>();
    }

    public void addAll(Collection<LotteryTicket> collection) {
        mLotteryTickets.clear();
        mLotteryTickets.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottery_ticket, parent, false);
        return new LotteryTicketViewHolder(view, mPresenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LotteryTicketViewHolder lotteryTicketViewHolder = (LotteryTicketViewHolder) holder;
        LotteryTicket superHero = mLotteryTickets.get(position);
        lotteryTicketViewHolder.render(superHero);
    }

    @Override
    public int getItemCount() {
        return mLotteryTickets.size();
    }
}