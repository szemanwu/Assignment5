package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if (tuplesResult.isEmpty()) {
			ArrayList<Tuple> temp;
			temp = new ArrayList<Tuple>();
		
			Tuple tuple;
			while ((tuple = child.next()) != null) {
				temp.add(tuple);
			}
		
			if (temp.isEmpty())
				return null;
			else {
				int a = 0;
				while (!temp.get(0).getAttributeName(a).equals(orderPredicate)) {
					a += 1;
				}
		
				while (!temp.isEmpty()) {
					int pos = 0;
					for (int i = 0; i < temp.size(); i++) {
						if (temp.get(i).getAttributeValue(a).toString().compareTo	
								(temp.get(pos).getAttributeValue(a).toString()) < 0)
							pos = i;
					}
					tuplesResult.add(temp.get(pos));
					temp.remove(pos);
				}
			}
		}
		Tuple tuple = tuplesResult.remove(0);
		return tuple;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}