package com.mrebollob.loteriadenavidad.data.repository.datasources.sp;

import android.content.SharedPreferences;

import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotterySPDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetSPUpdatedTimeException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.SetSPUpdatedTimeException;

import java.util.Date;

/**
 * @author mrebollob
 */
public class LotterySPDataSourceImp implements LotterySPDataSource {

    private final SharedPreferences sharedPreferences;

    private static final String LAST_UPDATE_KEY = "last_update_key";

    public LotterySPDataSourceImp(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Date getLastUpdatedTime() throws GetSPUpdatedTimeException {
        try {
            Date updateTime = new Date(sharedPreferences.getLong(LAST_UPDATE_KEY, 0));
            if (updateTime.getTime() != 0) {
                return updateTime;
            } else {
                throw new GetSPUpdatedTimeException();
            }
        } catch (Exception e) {
            throw new GetSPUpdatedTimeException();
        }
    }

    @Override
    public void setLastUpdatedTime(Date updateTime) throws SetSPUpdatedTimeException {

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong(LAST_UPDATE_KEY, updateTime.getTime());
            editor.commit();
        } catch (Exception e) {
            throw new SetSPUpdatedTimeException();
        }
    }
}
