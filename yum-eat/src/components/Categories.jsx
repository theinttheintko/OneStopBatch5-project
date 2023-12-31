import { categories } from '../data/data'
import React from 'react'

const Categories = () => {
  return (
    <div className='max-w-[1520px] m-auto px-4 py-2'>
        <h1 className='text-orange-500 font-bold text-2xl text-center py-2'>Tranding Categories</h1>
       <div className='grid grid-cols-2 md:grid-cols-6 gap-5 py-5 px-2'>
             {
                categories.map((item) =>(
                      <div key={item.id}  className='p-4 flex justify-center items-center hover:scale-105 duration-300'>
                        <img src={item.image} alt="" />
                      </div>
                ))
             }
       </div>
    </div>
  )
}

export default Categories