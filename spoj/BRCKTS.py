class Node:
	open_bracket_count = 0
	closed_bracket_count = 0
	total_sum = 0
	start_index = 0
	end_index = 0  

	def __init__(self):
		pass

def create_tree(tree, input_string, current_node_index, start_index, end_index):

	if start_index > end_index:
		return

	if start_index == end_index:
		new_node = Node()

		if input_string[start_index] == '(':
			new_node.open_bracket_count = 1
			new_node.closed_bracket_count = 0
			new_node.total_sum = 1
		else:
			new_node.open_bracket_count = 0
			new_node.closed_bracket_count = 1
			new_node.total_sum = -1

		new_node.start_index = start_index
		new_node.end_index = start_index

		tree[current_node_index] = new_node

		return
	
	mid = (start_index + end_index) / 2
	create_tree(tree, input_string, current_node_index*2 + 1, start_index, mid)
	create_tree(tree, input_string, current_node_index*2 + 2, mid + 1, end_index)

	left_child =  tree[current_node_index*2 + 1];
	right_child = tree[current_node_index*2 + 2];

	curr_node = Node()
	curr_node.open_bracket_count = left_child.open_bracket_count + right_child.open_bracket_count;
	curr_node.closed_bracket_count = left_child.closed_bracket_count + right_child.closed_bracket_count;
	curr_node.total_sum = left_child.total_sum + right_child.total_sum;
	curr_node.start_index = left_child.start_index;
	curr_node.end_index = right_child.end_index;

	tree[current_node_index] = curr_node;


def update_tree(tree, update_index):
	if update_index < 0:
		return

	curr_node = tree[update_index]

	if curr_node.start_index == curr_node.end_index:
		if curr_node.open_bracket_count == 1:
			curr_node.open_bracket_count = 0
			curr_node.total_sum = -1
			curr_node.closed_bracket_count = 1
		else:
			curr_node.open_bracket_count = 1
			curr_node.total_sum = 1
			curr_node.closed_bracket_count = 0


	else:
		left_child =  tree[update_index*2 + 1];
		right_child = tree[update_index*2 + 2];

		curr_node.open_bracket_count = left_child.open_bracket_count + right_child.open_bracket_count;
		curr_node.closed_bracket_count = left_child.closed_bracket_count + right_child.closed_bracket_count;
		curr_node.total_sum = left_child.total_sum + right_child.total_sum;
		curr_node.start_index = left_child.start_index;
		curr_node.end_index = right_child.end_index;

	tree[update_index] = curr_node

	if update_index % 2 == 0:
		update_tree(tree, update_index/2 -1)
	else:
		update_tree(tree, update_index/2)

for count in range (0, 10):
	length = int(raw_input())
	input_string = raw_input()

	tree = [0 for i in range(2*length-1)]

	create_tree(tree, input_string, 0, 0, length - 1)

	no_test_cases = int(raw_input())

	print "Test " + str(count+1) + ":\n"

	for j in range (0, no_test_cases):
		curr_type = int(raw_input())
		if curr_type == 0:

			if tree[0].total_sum == 0 and tree[1].total_sum >= 0 and tree[2].total_sum <= 0:
				print "YES\n"
			else:
				print "NO\n"
		else:
			update_tree(tree, length - 2 + curr_type)





