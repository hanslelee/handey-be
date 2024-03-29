package com.handey.web.history;

import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import com.handey.web.finishedweekly.FwBox;
import com.handey.web.finishedweekly.FwService;
import com.handey.web.todohistory.ToDoBoxHst;
import com.handey.web.todohistory.ToDoBoxHstService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HistoryController {
    private final ResponseService responseService;
    private final ToDoBoxHstService toDoBoxHstService;
    private final FwService fwService;

    public HistoryController(ResponseService responseService, ToDoBoxHstService toDoBoxHstService, FwService fwService) {
        this.responseService = responseService;
        this.toDoBoxHstService = toDoBoxHstService;
        this.fwService = fwService;
    }

    /**
     * 회원 투두 + 애프터 히스토리 전체 조회
     */
    @GetMapping("/user/{userId}/history")
    public ListResponse<History> getHstListByUserId(@PathVariable Long userId) {
        List<ToDoBoxHst> toDoBoxHstList = toDoBoxHstService.getToDoBoxHstListByUserId(userId);
        List<FwBox> fwBoxList = fwService.getFwBoxListByUserId(userId);
        Map<LocalDate, History> hstMap = new HashMap<>();

        toDoBoxHstList.forEach(toDoBoxHst -> {
            LocalDate saveDt = toDoBoxHst.getSaveDt();
            if(hstMap.containsKey(saveDt)){
                hstMap.get(saveDt).toDoBoxHstList.add(toDoBoxHst);
            }
            else {
                History newHistory = new History();
                newHistory.toDoBoxHstList = new ArrayList<>();
                newHistory.fwBoxList = new ArrayList<>();
                newHistory.setSaveDt(saveDt);
                newHistory.toDoBoxHstList.add(toDoBoxHst);
                hstMap.put(saveDt, newHistory);
            }
        });

        fwBoxList.forEach(fwBox -> {
//            LocalDate dt = fwBox.getSaveDt().minus(Period.ofDays(1));
            LocalDate dt = fwBox.getSaveDt();
            if(hstMap.containsKey(dt)) {
                hstMap.get(dt).fwBoxList.add(fwBox);
            }
        });
        List<History> hstList = new ArrayList<>(hstMap.values());
        return responseService.returnListResponse(hstList);
    }

    /**
     * 회원 투두 + 애프터 히스토리 박스 하나 단건 조회
     */
    @GetMapping("/user/{userId}/history/date")
    public SingleResponse<History> getHistoryBoxByUserIdAndDate(@PathVariable Long userId, @RequestParam String dt) {
        LocalDate searchDt = LocalDate.parse(dt, DateTimeFormatter.ISO_DATE);
        History historyBox = new History();
        List<ToDoBoxHst> toDoBoxHstList = toDoBoxHstService.getToDoBoxHstListByUserIdAndDate(userId, searchDt);
        List<FwBox> fwBoxHstList = fwService.getFwBoxListByUserIdAndDate(userId, searchDt);
        historyBox.setSaveDt(searchDt);
        historyBox.setToDoBoxHstList(toDoBoxHstList);
        historyBox.setFwBoxList(fwBoxHstList);
        return responseService.returnSingleResponse(historyBox);
    }
}
