package com.mrebollob.loteriadenavidad.presentation.presenters;

import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.interactors.CreateLotteryTicketFail;
import com.mrebollob.loteriadenavidad.presentation.interactors.CreateLotteryTicketSuccess;
import com.mrebollob.loteriadenavidad.presentation.interactors.UpdateLotteryTicketFail;
import com.mrebollob.loteriadenavidad.presentation.interactors.UpdateLotteryTicketSuccess;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.PresentationLotteryTicketMapper;
import com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform.LotteryTicketFormPresenter;
import com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform.LotteryTicketFormView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * @author mrebollob
 */
public class LotteryTicketFormPresenterTest {

    @Mock
    PresentationLotteryTicketMapper lotteryTicketMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createLotteryTicketSuccess() {

        CreateLotteryTicketSuccess createLotteryTicket = new CreateLotteryTicketSuccess(null, null, null);
        LotteryTicketFormView view = getView();
        LotteryTicketFormPresenter presenter = initializePresenter(createLotteryTicket, null,
                view, lotteryTicketMapper);

        presenter.createLotteryTicket(mock(PresentationLotteryTicket.class));
        verify(view).showCreateOrUpdateLotteryTicketSuccess();
    }

    @Test
    public void createLotteryTicketFail() {

        CreateLotteryTicketFail createLotteryTicket = new CreateLotteryTicketFail(null, null, null);
        LotteryTicketFormView view = getView();
        LotteryTicketFormPresenter presenter = initializePresenter(createLotteryTicket, null,
                view, lotteryTicketMapper);

        presenter.createLotteryTicket(mock(PresentationLotteryTicket.class));
        verify(view).showCreateLotteryTicketError();
    }

    @Test
    public void updateLotteryTicketSuccess() {

        UpdateLotteryTicketSuccess updateLotteryTicket = new UpdateLotteryTicketSuccess(null, null, null);
        LotteryTicketFormView view = getView();
        LotteryTicketFormPresenter presenter = initializePresenter(null, updateLotteryTicket,
                view, lotteryTicketMapper);

        presenter.updateLotteryTicket(mock(PresentationLotteryTicket.class));
        verify(view).showCreateOrUpdateLotteryTicketSuccess();
    }

    @Test
    public void updateLotteryTicketFail() {

        UpdateLotteryTicketFail updateLotteryTicket = new UpdateLotteryTicketFail(null, null, null);
        LotteryTicketFormView view = getView();
        LotteryTicketFormPresenter presenter = initializePresenter(null, updateLotteryTicket,
                view, lotteryTicketMapper);

        presenter.updateLotteryTicket(mock(PresentationLotteryTicket.class));
        verify(view).showUpdateLotteryTicketError();
    }

    private LotteryTicketFormView getView() {
        return mock(LotteryTicketFormView.class);
    }

    private LotteryTicketFormPresenter initializePresenter(CreateLotteryTicket createLotteryTicket,
                                                           UpdateLotteryTicket updateLotteryTicket,
                                                           LotteryTicketFormView view,
                                                           PresentationLotteryTicketMapper lotteryTicketMapper) {
        return new LotteryTicketFormPresenter(createLotteryTicket, updateLotteryTicket, view, lotteryTicketMapper);
    }
}
