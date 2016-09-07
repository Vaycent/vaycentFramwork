package HelpFulClass;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Formatter;

import BaseClass.BaseValue;
import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;


public class GetNFCActivity extends Activity {
    // NFC
    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcPendingIntent;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_loading);


        BaseValue.isNFCLoad=true;
        Log.i("NFC", "-----------------------NFC----------------------------");

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // Handle all of our received NFC intents in this activity.
        //*******FLAG_ACTIVITY_SINGLE_TOP*******swith to the old activity
        //*******FLAG_ACTIVITY_CLEAR_TOP********kill all the activity and new this activity
        // Intent filters for reading a note from a tag or exchanging over p2p.
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        String Uid=getNFCMessage(getIntent());

        finishActivities();

        System.out.println("NFC get Uid:" + Uid);

        mlog.v("----------------------------- NFC finish -----------------------------");




    }

    private void finishActivities() {
        this.finish();
    }

    @Override
    public void onResume() {
        System.out.println("NFC----onResume");
        super.onResume();

    }

    @Override
    public void onPause() {
        System.out.println("NFC----onPause");
        super.onPause();
    }

    private String ByteArrayToHexString(byte[] inarray) { // converts byte arrays to string
        int i, j, in;
        String[] hex = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
        };
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
            if(j<inarray.length-1){
                out+=":";
            }
        }
        return out;
    }




    //get NFC data text
    private String getNFCMessage(Intent intent) {
        System.out.println("getIntent().getAction():" + intent.getAction());

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {

            String TAG = "NFC Loading";
            // Parse the intent
            Log.i(TAG, "getNdefMessages");
            NdefMessage[] msgs = null;
            NdefMessage[] msgsUid = null;

            String action = intent.getAction();

            if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)|| NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                Log.i(TAG,"getNdefMessages - ACTION_TAG_DISCOVERED||ACTION_NDEF_DISCOVERED");

                //text/plain
                Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                if (rawMsgs != null) { //can get the NDEF_MESSAGES data
                    Log.i(TAG, "getNdefMessages -  rawMsgs != null+length="+ rawMsgs.length);
                    msgs = new NdefMessage[rawMsgs.length];
                    for (int i = 0; i < rawMsgs.length; i++) {
                        msgs[i] = (NdefMessage) rawMsgs[i];
                    }
                } else {// Unknown tag type
                    Log.i(TAG, "getNdefMessages -  Unknown tag type");
                    byte[] empty = new byte[] {};
                    NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN,empty, empty, empty);
                    NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
                    msgs = new NdefMessage[] { msg };
                }
                //nfc uid
                String result=ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));


                System.out.println("resut:"+result);


                //vaycent
//					result="04:69:94:92:16:3c:81";
                if(result!=null){
                    String uid = result.toUpperCase();
                    return uid;
                }




            } else {
                Log.d(TAG, "Unknown intent.");
            }

            try {
                byte[] payload = msgs[0].getRecords()[0].getPayload();
					/*
					 * payload[0] contains the "Status Byte Encodings" field, per the
					 * NFC Forum "Text Record Type Definition" section 3.2.1.
					 *
					 * bit7 is the Text Encoding Field.
					 *
					 * if (Bit_7 == 0): The text is encoded in UTF-8 if (Bit_7 == 1):
					 * The text is encoded in UTF16
					 *
					 * Bit_6 is reserved for future use and must be set to zero.
					 *
					 * Bits 5 to 0 are the length of the IANA language code.
					 */
//                String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
                String textEncoding;
                if((payload[0] & 0200) == 0){
                    textEncoding="UTF-8";
                }else{
                    textEncoding="UTF-16";
                }




                int languageCodeLength = payload[0] & 0077;
                String languageCode = new String(payload, 1, languageCodeLength,
                        "US-ASCII");
                String text = new String(payload, languageCodeLength + 1,payload.length - languageCodeLength - 1, textEncoding);
                Log.i("NFC", "Message------------>" + text);

                return text;
            } catch (UnsupportedEncodingException e) {
                // should never happen unless we get a malformed tag.
                throw new IllegalArgumentException(e);
            }
        }
        else if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())){
            //���_ͨ
            Tag tag 			= (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] tagIDBytes 	= tag.getId();
            String tagId 		= mBytesToHexString(tagIDBytes);
            // build up the output string
//			StringBuilder sb 	= new StringBuilder();
//			sb.append(tagId);
//			sb.append("\n\n");
//			sb.append(action);
//			sb.append("\n\n");
//			for (int y = 0; y < tag.getTechList().length; y++) {
//				sb.append('\n'); //
//				sb.append(tag.getTechList()[y]);
//			}
//			TextView t = (TextView) findViewById(R.id.textView1);
//			t.setText(sb);
            System.out.println("tagId:"+tagId);
            return tagId;
        }
        else if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            //���ͨ
            return "22:33";
        }

        else{
            return null;
        }
    }
    private static String mBytesToHexString(byte[] bytes) {
        StringBuilder 	sb 		  = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {	formatter.format("%02x", b);  }
        String tempStr=sb.toString().toUpperCase();

        char [] stringArr = tempStr.toCharArray();

        String resultStr="";


        for(int i=0;i<stringArr.length;){
            resultStr=resultStr+stringArr[i];
            i++;
            if(i%2==0&&i<=stringArr.length-1){
                resultStr=resultStr+":";
            }
        }
        System.out.println("resultStr:"+resultStr);

        return  resultStr;
    }

}
