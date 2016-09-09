package Ormlite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_bp")
public class BP
{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "sys")
    private String sys;
    @DatabaseField(columnName = "dia")
    private String dia;
    @DatabaseField(columnName = "hr")
    private String hr;

    @DatabaseField(columnName = "recordtime")
    private String recordtime;

    public BP()
    {
    }

    public BP(String sys, String dia, String hr, String recordtime)
    {
        this.sys = sys;
        this.dia = dia;
        this.hr = hr;
        this.recordtime=recordtime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSys()
    {
        return sys;
    }

    public void setSys(String value)
    {
        this.sys = value;
    }

    public String getDia()
    {
        return dia;
    }

    public void setDia(String value)
    {
        this.dia = value;
    }

    public String getHr()
    {
        return hr;
    }

    public void setHr(String value)
    {
        this.hr = value;
    }


    public String getRecordtime()
    {
        return recordtime;
    }

    public void setRecordtime(String value)
    {
        this.recordtime = value;
    }
}
