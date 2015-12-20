package com.mrebollob.loteriadenavidad.domain.repository.datasources;

import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetSPUpdatedTimeException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.SetSPUpdatedTimeException;

import java.util.Date;

/**
 * @author mrebollob
 */
public interface LotterySPDataSource {

    Date getLastUpdatedTime() throws GetSPUpdatedTimeException;

    void setLastUpdatedTime(Date updateTime) throws SetSPUpdatedTimeException;
}
