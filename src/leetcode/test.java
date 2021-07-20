package leetcode;

class test {
    public int search(int[] nums, int target) {
        int n=nums.length;
        int a=0;
        for(int i=0;i<=n;i++){
            if(nums[i]==target){
                a++;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        int target=3;
    }
}