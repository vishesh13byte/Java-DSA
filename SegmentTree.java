
    /*
      This is template for range sum query with updates
      We can modify for different purposes, min/max, gcd, lcm, etc
      
      left and right are consistently used for the range while building, updating, querying 
      [start,end] are query range endpoints
    */
    int[] nums, segTree;
    int n;
    public void solve(int[] nums) { // constructor, nums is input array
        this.nums = nums;
        this.n = nums.length;
        this.segTree = new int[4 * n];
        build(0,n-1,0);
    }
    
    private void build(int left, int right, int idx){
        if(left == right){
            segTree[idx] = nums[left];
            return;
        }
        int mid = left + (right - left)/2;
        build(left,mid,2*idx+1);
        build(mid+1,right,2*idx+2);
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }
    
    private int query(int left, int right, int start, int end, int idx){
        if(left > end || right < start){
            return 0;
        }
        if(start <= left && right <= end){
            return segTree[idx];
        }
        int mid = left + (right - left)/2;
        return query(left,mid,start,end,2*idx+1) + query(mid+1,right,start,end,2*idx+2);
    }
    
    private void update(int idx, int left, int right, int index, int val){
        if(left == right){
            segTree[idx]=val;
            return;
        }
        
        int mid = left + (right - left)/2;
        if(index <= mid) update(2*idx + 1,left,mid,index,val);
        else update(2*idx +2, mid+1,right,index,val);
        segTree[idx] = segTree[2*idx + 1] + segTree[2*idx + 2]; 
    }
    
    


