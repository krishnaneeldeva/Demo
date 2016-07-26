var ang = angular.module('myApp', []);

PostComment = ""
ang.controller('postCtrl', function($scope){

	$scope.details=[];
	//$scope.Comments= [];

	$scope.DSubmit= function()
	{
		$scope.details.push( {postcontent: $scope.PostContent, commentcontent: []});
		console.log($scope.details);
		$scope.Comments= [];
	};


	$scope.CSubmit = function(comment,index){
		var len = $scope.details.length;
		for(var i =0; i< len; i++ ){
			
			console.log($scope.details[i]);
			var obj = $scope.details[i];
				console.log($scope.details[i]["postcontent"]);
				console.log($scope.details[i]["postcontent"] == $scope.PostContent);
				if($scope.details[i]["postcontent"] == $scope.PostContent){
					$scope.details[i]["commentcontent"].push(comment);
					console.log("after" + $scope.details[i]);
				}
		}
		console.log(index);
		$scope.Comments = $scope.details[index]["commentcontent"];
		console.log($scope.Comments);
	};

	
});
