package com.mrebollob.loteriadenavidad.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryTicket;

import java.sql.SQLException;

/**
 * @author mrebollob
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(String databaseName, final Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BddLotteryTicket.class);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to create tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BddLotteryTicket.class, true);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to drop tables", e);
        }
    }

    public Dao<BddLotteryTicket, Integer> getLotteryTicketDao() {
        try {
            return getDao(BddLotteryTicket.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}