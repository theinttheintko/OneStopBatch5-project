import { mealData } from '../data/data'
import { ArrowRightIcon } from "@heroicons/react/24/solid";
import React, { useState } from 'react'

const Meal = () => {
   const [foods,setFoods] = useState(mealData)
   const filterCat = (cat) =>{
    setFoods(
        mealData.filter((item) => {
            return item.category === cat
        })
    )
   }
  return (
    <div className='max-w-[1520px] m-auto px-4 py-12'>
        <h1 className='text-orange-500 font-bold text-2xl text-center py-2'>Our Meal</h1>
        <div className='flex flex-col lg:flex-row justify-center m-3'>
            <div className='flex justify-center md:justify-center' >
                <button onClick={() =>setFoods(mealData)} className='m-1 border-orange-700 text-white bg-orange-700 hover:bg-white hover:text-orange-700 hover:border-orange-700'>All</button>
                <button onClick={() => filterCat('pizza')} className='m-1 border-orange-700 text-white bg-orange-700 hover:bg-white  hover:text-orange-700 hover:border-orange-700'>Pizza</button>
                <button onClick={() => filterCat('salad')} className='m-1 border-orange-700 text-white bg-orange-700 hover:bg-white  hover:text-orange-700 hover:border-orange-700'>Salad</button>
                <button onClick={() => filterCat('noodle')} className='m-1 border-orange-700 text-white bg-orange-700 hover:bg-white  hover:text-orange-700 hover:border-orange-700'>Noodle</button>
                <button onClick={() => filterCat('food')} className='m-1 border-orange-700 text-white bg-orange-700 hover:bg-white  hover:text-orange-700 hover:border-orange-700'>Food</button>
                <button onClick={() => filterCat('burger')} className='m-1 border-orange-700 text-white bg-orange-700 hover:bg-white  hover:text-orange-700 hover:border-orange-700'>Burger</button>

            </div> 
        </div>
        <div className='grid md:grid-cols-2 sm:grid-cols-1 lg:grid-cols-4 gap-6' >
             {
                foods.map((item) => {
                    return( 
                       
                        <div className='border-none hover:scale-105 duration-300 ' key={item.id}>
                                <img className='w-full h-[200px] object-cover rounded-lg'
                                src={item.image} alt="" />
                           <div className='flex justify-between py-2 px-4'>
                            <p className='font-bold '>{item.name}</p>
                            <p className='bg-orange-700 h-18 w-18 rounded-full -mt-10 text-white py-4 px-2 border-8 font-bold'>{item.price}</p>
                           </div>
                           <div className='pl-2 py-4'>
                            <p className='flex items-center text-indigo-500'>View More
                            <ArrowRightIcon className='w-5 ml-2 cursor-pointer'></ArrowRightIcon></p>
                           </div>

                        </div>
                    )
                   
                })
             }
        </div>
    </div>
  )
}

export default Meal