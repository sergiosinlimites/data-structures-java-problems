import java.util.HashMap;

public class twoSum {

  public void main(String[] args) {
    int[] startArray = {2,7,11,15};
    this.twoSumFromArr(startArray, 9);
  }

  public int[] twoSumFromArr(int[] nums, int target){
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for(int currentIndex = 0; currentIndex < nums.length; currentIndex++){
      int newNum = nums[currentIndex];
      Integer existingNum = map.get(target - newNum);
      if(existingNum != null && existingNum.intValue() != -1){
        int[] solution = {existingNum, currentIndex};
        return solution;
      } else {
        map.put(newNum, currentIndex);
      }
    }
    int[] notFound = {-1,-1};
    return notFound;
  }
}
