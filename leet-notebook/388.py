class Solution(object):
    def lengthLongestPath(self, input):
        """
        :type input: str
        :rtype: int
        """
        max_length = 0
        parent_length = []
        self_length = 0
        depth = 0
        isFile = False
        for i in input:
            if i == '\n':
                # Find depth
                if depth == 0:
                    total_len = self_length
                else:
                    total_len = parent_length[depth - 1] + self_length
                # If it's a file
                if isFile:
                    max_length = max(total_len, max_length)
                    isFile = False
                else:
                    try:
                        del parent_length[depth]
                    except IndexError:
                        pass
                    finally:
                        total_len += 1
                        parent_length.insert(depth, total_len)
                    print parent_length
                # reset
                self_length = 0
                depth = 0
            elif i == '\t':
                depth += 1
            else:
                self_length += 1
                if i == '.':
                    isFile = True
        # and finally because the given string does not end with \n
        if isFile:
            if depth == 0:
                total_len = self_length
            else:
                total_len = parent_length[depth - 1] + self_length
            max_length = max(max_length, total_len)
        return max_length

a = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
a = """dir
    subdir1
	subdir2
		file.ext"""
a = "dir\n\t        file.txt\n\tfile2.txt"
sol = Solution()
print sol.lengthLongestPath(a)

