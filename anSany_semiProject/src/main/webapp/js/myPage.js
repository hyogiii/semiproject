function myPageQnADelete(qnaNo){
	if(confirm("문의내역을 삭제하시겠습니까?")){
		location.href="/myPageQnADelete.do?qnaNo="+qnaNo;
	}
}