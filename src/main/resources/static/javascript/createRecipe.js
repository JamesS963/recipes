let ingredientButton = document.getElementById("addIngredient");


let ingredientIndex=document.querySelectorAll('.ingredient').length;

ingredientButton.addEventListener("click",() =>{
    document.getElementById("ingredients").
    insertAdjacentHTML("beforeend",`
   			<div>
				<label for="ingredient">ingredient</label> 
				<input type="text" name="ingredients[${ingredientIndex}].name" class="ingredient"> 
				
				<label for="amount">amount</label> 
				<input type="text" name="ingredients[${ingredientIndex}].amount">
			</div>
    		`);
    ingredientIndex+=1;
});


let instructionButton = document.getElementById("addInstruction");

let instructionNo = document.querySelectorAll('.instruction').length+1;

let instructionIndex= document.querySelectorAll('.instruction').length;

instructionButton.addEventListener("click",() =>{
    document.getElementById("instructions").
    insertAdjacentHTML("beforeend",`
    <div>
    	<label for="instruction">${instructionNo}.</label> 
		<input type="text" class="instruction" name="instructions[${instructionIndex}].instruction" >
	</div>
    		`);
    instructionNo+=1;
    instructionIndex+=1;
});