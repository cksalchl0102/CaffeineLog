package kr.co.caffeinelog;

import kr.co.caffeinelog.Common.ExpandableListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddViewFragment extends Fragment {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView elv;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.addview_fragment, null);

        elv = (ExpandableListView) rootView.findViewById(R.id.list);
        ChildListData();

        listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);

        elv.setAdapter(listAdapter);
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Group뷰 펼칠 때 이벤트
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " 선택",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Group뷰 닫힐 때 이벤트
        elv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " 선택 취소",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Child뷰 클릭 이벤트
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //db에서 가져온 카페인함량 caffeineAmountView에 더하는 코드 추가해야함
                Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
        return rootView;
    }

    //Group뷰 타이틀 및 Child뷰 데이터 넣는 곳
    private void ChildListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // 그룹 생성
        listDataHeader.add("Coffee");
        listDataHeader.add("Tea");
        listDataHeader.add("EnergyDrink");
        listDataHeader.add("Soda");
        listDataHeader.add("Chocolate");

        // 그룹 내 차일드 뷰 생성
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
        Chocolate.add("72%드립카카오");
        Chocolate.add("핫 초코");

        //데이터 적용
        listDataChild.put(listDataHeader.get(0), Coffee);
        listDataChild.put(listDataHeader.get(1), Tea);
        listDataChild.put(listDataHeader.get(2), EnergyDrink);
        listDataChild.put(listDataHeader.get(3), Soda);
        listDataChild.put(listDataHeader.get(4), Chocolate);
    }
}

