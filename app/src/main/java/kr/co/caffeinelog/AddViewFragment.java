package kr.co.caffeinelog;

import kr.co.caffeinelog.Common.ExpandableListAdapter;
import kr.co.caffeinelog.Common.addViewDialogFragment;
import kr.co.caffeinelog.ConnectDB.CaffeineScopeDAO;
import kr.co.caffeinelog.Model.AddCaffeineBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddViewFragment extends Fragment {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView elv;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private FloatingActionButton fabBtn;
    private TextView caffeineAmountView;
    private int totalCaffeine;
    private int value;
    //DB연동 코드 추가
    private String caffeineSort = "";
    private String caffeineName = "";
    private CaffeineScopeDAO caffeineScopeDAO = new CaffeineScopeDAO();

    SharedPreferences caffeinePrefs;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.addview_fragment, null);

        caffeinePrefs = this.getActivity().getSharedPreferences("daily_caffeine", 0);
        editor = caffeinePrefs.edit();

        ((Main2Activity)getActivity()).checkDate();
        totalCaffeine = caffeinePrefs.getInt("intake_caffeine", 0);

        fabBtn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fabBtn.setOnClickListener(new FABClickListener());
        caffeineAmountView = (TextView) rootView.findViewById(R.id.caffeine);
        caffeineAmountView.setText(totalCaffeine + "");

        elv = (ExpandableListView) rootView.findViewById(R.id.list);
        ChildListData();

        listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);
        elv.setAdapter(listAdapter);

        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Group뷰 펼칠 때 이벤트
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast toast = Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " 선택",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,190);
                toast.show();
                caffeineSort = listDataHeader.get(groupPosition);
            }
        });

        // Group뷰 닫힐 때 이벤트
        elv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast toast = Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " 선택 취소",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0,190);
                toast.show();

            }
        });

        // Child뷰 클릭 이벤트
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        final int groupPosition, final int childPosition, long id) {
                //db에서 가져온 카페인함량 caffeineAmountView에 더하는 코드 추가해야함

                caffeineName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                value = 0;
                Log.i("chanmi", "in daaClass : 카페인 디비 보낼 값 테이블이름 : " + caffeineSort + ", 값 : " + caffeineName);
                if (caffeineScopeDAO.addCaffeine(caffeineSort, caffeineName).equals("false")) {
                    Log.i("chanmi", "in add Class : DB 연동 실패");
                    // value = Integer.parseInt(addCaffeineBean.getValue());
                } else  {
                    value = Integer.parseInt(caffeineScopeDAO.addCaffeine(caffeineSort, caffeineName));
                    Log.i("chanmi", "in add Class : DB 성공");

                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("카페인 추가")
                        .setMessage(listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition) + "를 추가하시겠습니까?\n +"+value+"mg")
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        ((Main2Activity)getActivity()).checkDate();
                                        totalCaffeine = caffeinePrefs.getInt("intake_caffeine", 0);
                                        totalCaffeine += value;

                                        //하루 카페인 섭취량 변경
                                        editor.putInt("intake_caffeine", totalCaffeine);
                                        editor.apply();

                                        caffeineAmountView.setText(totalCaffeine + "");
                                        Toast toast = Toast.makeText(getActivity(), "+"+value, Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.BOTTOM,0,190);
                                        toast.show();
                                    }
                                }).setNegativeButton("취소", null);
                builder.setCancelable(false);//다이얼로그 바깥영역 터치 시 종료되지 않도록 함
                builder.create().show();
                return false;
            }
        });
        return rootView;
    }

    //Group뷰 타이틀 및 Child뷰 데이터 넣는 곳
    private void ChildListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Group 생성
        listDataHeader.add("coffee");
        listDataHeader.add("tea");
        listDataHeader.add("energydrink");
        listDataHeader.add("soda");
        listDataHeader.add("chocolate");

        // Group 내 Child뷰 생성
        List<String> Coffee = new ArrayList<String>();
        Coffee.add("샷 추가");
        Coffee.add("에스프레소");
        Coffee.add("아메리카노 Tall");
        Coffee.add("아메리카노 Grande");
        Coffee.add("아메리카노 Venti");
        Coffee.add("카페라떼 Tall");
        Coffee.add("카페라떼 Grande");
        Coffee.add("카페라떼 Venti");
        Coffee.add("콜드브루 Tall");
        Coffee.add("콜드브루 Grande");
        Coffee.add("콜드브루 Venti");
        Coffee.add("카푸치노 Tall");
        Coffee.add("카푸치노 Grande");
        Coffee.add("카푸치노 Venti");
        Coffee.add("카라멜 마끼아또 Tall");
        Coffee.add("카라멜 마끼아또 Grande");
        Coffee.add("카라멜 마끼아또 Venti");

        List<String> Tea = new ArrayList<String>();
        Tea.add("녹차");
        Tea.add("홍차");
        Tea.add("아이스티");
        Tea.add("보리차");

        List<String> EnergyDrink = new ArrayList<String>();
        EnergyDrink.add("핫식스");
        EnergyDrink.add("핫식스 라이트");
        EnergyDrink.add("레드불 에너지 드링크");
        EnergyDrink.add("몬스터 에너지");
        EnergyDrink.add("박카스 100ml");

        List<String> Soda = new ArrayList<String>();
        Soda.add("코카콜라 250ml");
        Soda.add("코카콜라 제로 250");
        Soda.add("펩시 콜라 250ml");
        Soda.add("펩시 콜라 레몬 250ml");
        Soda.add("칠성 사이다");
        Soda.add("스프라이트");

        List<String> Chocolate = new ArrayList<String>();
        Chocolate.add("허쉬 밀크 초코렛");
        Chocolate.add("가나 마일드");
        Chocolate.add("노브랜드 밀크초콜렛");
        Chocolate.add("토블론 스위스 밀크초콜렛");
        Chocolate.add("젠느");
        Chocolate.add("고디바 초콜렛");
        Chocolate.add("72%드림카카오");
        Chocolate.add("핫 초코");

        //데이터 적용
        listDataChild.put(listDataHeader.get(0), Coffee);
        listDataChild.put(listDataHeader.get(1), Tea);
        listDataChild.put(listDataHeader.get(2), EnergyDrink);
        listDataChild.put(listDataHeader.get(3), Soda);
        listDataChild.put(listDataHeader.get(4), Chocolate);
    }

    //FloatingActionButton 클릭 이벤트 리스너
    class FABClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            addViewDialogFragment dialog = addViewDialogFragment.newInstance(new addViewDialogFragment.CaffeineInputListener() {
                @Override
                public void onCaffeineInputComplete(String caffeine) {
                    ((Main2Activity)getActivity()).checkDate();
                    totalCaffeine = caffeinePrefs.getInt("intake_caffeine", 0);
                    totalCaffeine += Integer.parseInt(caffeine);

                    //하루 카페인 섭취량 변경
                    editor.putInt("intake_caffeine", totalCaffeine);
                    editor.apply();

                    Toast toast = Toast.makeText(getActivity(), "+"+caffeine, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM,0,190);
                    toast.show();

                    caffeineAmountView.setText(totalCaffeine + "");
                }
            });
            dialog.show(getFragmentManager(), "addDialog");
            dialog.setCancelable(false);//다이얼로그 바깥영역 터치 시 종료되지 않도록 함
        }
    }
}

