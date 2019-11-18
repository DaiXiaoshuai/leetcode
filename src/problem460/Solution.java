package problem460;

import java.util.Arrays;

/**
 * TODO 妈的题目理解错了，是要统计使用频率，写成 最近最少使用 的算法了。。。。不高兴改了，太麻烦了
 */
class LFUCache {

    int[] dataArray;
    int[] keyArray;
    int unusedIndex; // 以此下标开始，keyarray中的都为未使用过的数据

    public LFUCache(int capacity) {
        this.dataArray = new int[capacity];
        this.keyArray = new int[capacity];
        unusedIndex = 0;
        for(int i=0;i<capacity;i++){
            keyArray[i] = Integer.MIN_VALUE;
            dataArray[i] = Integer.MIN_VALUE;
        }

    }

    public int get(int key) {
        // if(key ==3) print();
        if(dataArray.length == 0) return -1;
        int index = 0;
        while(index < keyArray.length){
            if(keyArray[index] == key) break;
            index++;
        }
        if(index >= keyArray.length) return -1;
        int tmpKey = 0;
        int tmpData = 0;
        if(index >= unusedIndex) unusedIndex++;
        while(index>0){
            tmpKey = keyArray[index];
            keyArray[index] = keyArray[index-1];
            keyArray[index-1] = tmpKey;

            tmpData = dataArray[index];
            dataArray[index] = dataArray[index-1];
            dataArray[index-1] = tmpData;
            index--;
        }
        // if(key ==3) print();
        return dataArray[0];
    }

    public void put(int key, int value) {
        if(dataArray.length == 0) return;
        int index = 0;
        for(;index <dataArray.length;index++) {
            if(keyArray[index] == Integer.MIN_VALUE) break;
            if(keyArray[index] == key) break;
        }
        if(index == dataArray.length||dataArray[index] == Integer.MIN_VALUE){ // 无key
            index = dataArray.length-1;
            if(unusedIndex>= keyArray.length){
                keyArray[keyArray.length-1] = key;
                dataArray[dataArray.length -1] = value;
                unusedIndex --;
            }else{
                while(index>unusedIndex){
                    dataArray[index] = dataArray[index-1];
                    keyArray[index] = keyArray[index-1];
                    index--;
                }
                dataArray[unusedIndex] = value;
                keyArray[unusedIndex] = key;
            }

            // keyArray[keyArray.length-1] = key;
            // dataArray[dataArray.length - 1] = value;
            // }else if(dataArray[index] == Integer.MIN_VALUE){
            //     keyArray[index] = key;
            //     dataArray[index] = value;
        }else{
            int tmpKey = 0;
            int tmpData = 0;
            if(index<unusedIndex || unusedIndex>=keyArray.length){
                while(index>0){
                    tmpKey = keyArray[index];
                    keyArray[index] = keyArray[index-1];
                    keyArray[index-1] = tmpKey;

                    tmpData = dataArray[index];
                    dataArray[index] = dataArray[index-1];
                    dataArray[index-1] = tmpData;
                    index--;
                }
                dataArray[0] = value;
            }
            else{
                while(index>unusedIndex){
                    tmpKey = keyArray[index];
                    keyArray[index] = keyArray[index-1];
                    keyArray[index-1] = tmpKey;

                    tmpData = dataArray[index];
                    dataArray[index] = dataArray[index-1];
                    dataArray[index-1] = tmpData;
                    index--;
                }
                // dataArray[0] = value;
                dataArray[unusedIndex] = value;
            }

        }

    }

    void print(){
        System.out.println("-----------");
        System.out.println(Arrays.toString(keyArray));
        System.out.println(Arrays.toString(dataArray));
    }
}
//["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
//        [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
