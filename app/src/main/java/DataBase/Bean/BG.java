package DataBase.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vaycent on 16/9/8.
 */
@DatabaseTable(tableName = "tb_bg")
public class BG {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "bgValue")
    private String bgValue;
    @DatabaseField(columnName = "type")
    private String type;


    @DatabaseField(columnName = "recordtime")
    private String recordtime;

    public BG()
    {
    }

    public BG(String bgValue, String type, String recordtime)
    {
        this.bgValue = bgValue;
        this.type = type;
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

    public String getBgValue()
    {
        return bgValue;
    }

    public void setBgValue(String value)
    {
        this.bgValue = value;
    }


    public String getType()
    {
        return type;
    }

    public void setType(String value)
    {
        this.type = value;
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
